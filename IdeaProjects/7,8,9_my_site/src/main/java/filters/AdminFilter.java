package filters;

import servlets.AutorityServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/update", "/insert",})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            String cookie = AutorityServlet.cookie.getValue();
            HttpSession session = request.getSession(false);
            Boolean isAuthenticated = false;
            Boolean sessionExists = session != null;
            Boolean isdoc = request.getRequestURI().equals("/insert");
            Boolean isserv = request.getRequestURI().equals("/update");

            if (sessionExists && (cookie.equals("admin"))) {
                isAuthenticated = (Boolean) session.getAttribute("authenticated");
                if (isAuthenticated == null) {
                    isAuthenticated = false;
                }
            }

            if ((!isAuthenticated && isdoc) || (!isAuthenticated && isserv)) {
                response.sendRedirect("/signIn");
            } else if ((isAuthenticated && isdoc) || (isAuthenticated && isserv)) {
                filterChain.doFilter(request, response);
            }
        } catch (NullPointerException e) {
            response.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
