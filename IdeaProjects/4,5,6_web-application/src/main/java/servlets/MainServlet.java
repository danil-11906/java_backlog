package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/main.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String site = request.getParameter("interface");
        int num = 0;
        if (site.equals("signIn")) {
            response.sendRedirect("/signIn");
            num++;
        }
        if (site.equals("users")) {
            response.sendRedirect("/users");
            num++;
        }
        if (site.equals("signUp")) {
            response.sendRedirect("/signUp");
            num++;
        }
        if (num == 0) {
            response.sendRedirect("/");
        }
    }
}
