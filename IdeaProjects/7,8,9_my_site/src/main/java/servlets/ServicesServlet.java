package servlets;

import interfaces.ServicesRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/services")
public class ServicesServlet extends HttpServlet {

    private ServicesRepository servicesRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servicesRepository = (ServicesRepository) config.getServletContext().getAttribute("servicesRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("servicesForJsp",servicesRepository.findAll());
        request.getRequestDispatcher("/jsp/services.jsp").forward(request, response);
    }
}
