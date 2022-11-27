package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.ServicesRepository;
import interfaces.UsersRepository;
import models.Reviews;
import models.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/update")
public class UpdateServicesServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    private ServicesRepository servicesRepository;
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
        servicesRepository = (ServicesRepository) config.getServletContext().getAttribute("servicesRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/InsertService.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = objectMapper.readValue(request.getReader(), Service.class);
        servicesRepository.insert(service);
        List<Service> services = servicesRepository.findAll();
        String servicesAsJson = objectMapper.writeValueAsString(services);
        response.setContentType("application/json");
        response.getWriter().println(servicesAsJson);
    }
}
