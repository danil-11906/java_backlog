package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/signIn", "/signUp", "/bag", "/calculator"})
public class AuthenticationFilter implements Filter {

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
        Boolean isLoginPage = request.getRequestURI().equals("/signIn");
        Boolean isRegistratoinPage = request.getRequestURI().equals("/signUp");


        if (sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");

            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }

        if ((!isAuthenticated && isLoginPage) || (!isAuthenticated && isRegistratoinPage))  {
            filterChain.doFilter(request, response);
        } else if ((isAuthenticated && isLoginPage) || (isAuthenticated && isRegistratoinPage)) {
            response.sendRedirect("/profile");
        }
        else if ((isAuthenticated && !isLoginPage) || (isAuthenticated && !isRegistratoinPage)) {
            filterChain.doFilter(request,response);
        }
        else {
            response.sendRedirect("/signIn");
        }

    }

    @Override
    public void destroy() {

    }
}
