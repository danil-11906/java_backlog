package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/profile")
public class ProfileFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        Boolean isProfilePage = request.getRequestURI().equals("/profile");


        if (sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");

            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }

        if (isAuthenticated && isProfilePage) {
            filterChain.doFilter(request, response);
        }
        else {
            response.sendRedirect("/signIn");
        }

    }

    @Override
    public void destroy() {

    }
}