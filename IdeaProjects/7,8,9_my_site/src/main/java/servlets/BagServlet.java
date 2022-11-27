package servlets;

import interfaces.ServicesRepository;
import interfaces.UsersRepository;
import models.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bag")
public class BagServlet extends HttpServlet {

    private ServicesRepository servicesRepository;
    private UsersRepository usersRepository;
    private List<Service> serv;


    @Override
    public void init(ServletConfig config) throws ServletException {
        servicesRepository = (ServicesRepository) config.getServletContext().getAttribute("servicesRepository");
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serv = CalkulatorServlet.serv;
        request.setAttribute("serviceUser", serv);
        request.getRequestDispatcher("/jsp/bag.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if (serv.size()==0){
                response.sendRedirect("/calculator");
            }
            else {
                String cookie = AutorityServlet.cookie.getValue();
                Long id = usersRepository.findId(cookie).getId();
                servicesRepository.insertOrder(id, serv);
                serv.clear();
                response.sendRedirect("/profile");
            }
        } catch (NullPointerException e) {
            response.sendRedirect("/calculator");
        }
    }
}
