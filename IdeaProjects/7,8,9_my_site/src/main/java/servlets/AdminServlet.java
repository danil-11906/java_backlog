package servlets;

import interfaces.UsersRepository;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookie = AutorityServlet.cookie.getValue();
        request.setAttribute("login",cookie);
        User log = User.builder()
                .login((String) request.getAttribute("login"))
                .build();
        request.setAttribute("userForJsp", usersRepository.findUser(log));
        request.getRequestDispatcher("/jsp/admin.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AutorityServlet.cookie.setMaxAge(0);
        AutorityServlet.httpSession.removeAttribute("authenticated");
        response.sendRedirect("/signIn");
    }
}
