package servlets;

import interfaces.DoctorsRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctors")
public class DoctorsListServlet extends HttpServlet {

    private DoctorsRepository doctorsRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        doctorsRepository = (DoctorsRepository) config.getServletContext().getAttribute("doctorsRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("DocsForJsp", doctorsRepository.findAll());
        req.getRequestDispatcher("/jsp/doctors.jsp").forward(req, resp);
    }
}