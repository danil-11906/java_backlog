package servlets;

import dto.SignInForm;
import interfaces.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signIn")
public class AutorityServlet extends HttpServlet {

    public static Cookie cookie;
    public static HttpSession httpSession;
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService"); // так же изменить на ServletConfig config
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/sign.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInForm form = new SignInForm();
        form.setEmail(request.getParameter("login"));
        form.setPassword(request.getParameter("password"));
        if (signInService.signIn(form) == 1) {
            httpSession = request.getSession(true);
            httpSession.setAttribute("authenticated", true);
            cookie = new Cookie("login",form.getEmail());
            response.addCookie(cookie);
            if (cookie.getValue().equals("admin")) {
                response.sendRedirect("/admin");
            }
            else {
                response.sendRedirect("/profile");
            }
        } else {
            response.sendRedirect("/signIn");
        }
    }
}