package servlets;

import interfaces.ServicesRepository;
import models.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/calculator")
public class CalkulatorServlet extends HttpServlet {

    private ServicesRepository servicesRepository;
    public static List<Service> serv;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servicesRepository = (ServicesRepository) config.getServletContext().getAttribute("servicesRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("servicesForJsp", servicesRepository.findAll());
        request.getRequestDispatcher("/jsp/calculator.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Service> a = servicesRepository.findAll();
        serv = new ArrayList<>();
        for (Service service : a) {
            if (Objects.equals(request.getParameter(service.getName()), "on")) {
                String name = service.getName();
                Service model = Service.builder()
                        .name(name)
                        .cost(servicesRepository.findCost(name))
                        .build();
                serv.add(model);
            }
        }
        response.sendRedirect("/bag");
    }
}
