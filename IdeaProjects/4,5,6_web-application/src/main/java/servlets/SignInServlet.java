package servlets;

import functionals.CookiesRepositoryJdbcImpl;
import functionals.UsersRepositoryJdbcImpl;
import interfaces.CookiesRepository;
import interfaces.UsersRepository;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qawsedrf102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-906";

    private UsersRepository usersRepository;
    private CookiesRepository cookiesRepository;

    @Override
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersRepository = new UsersRepositoryJdbcImpl(connection, statement);
            cookiesRepository = new CookiesRepositoryJdbcImpl(connection,statement);
        } catch (SQLException throwables) {
            throw  new IllegalStateException(throwables);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/sign.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User log = User.builder()
                .login(login)
                .build();
        try {
            Optional<User> result = usersRepository.findByLogin(log);
            if(result.isPresent()) {
                if (result.get().getPassword().equals(password)) {
                    String id = UUID.randomUUID().toString();
                    Cookie userCookie = new Cookie("uuid",id);
                    cookiesRepository.save(userCookie,result.get().getId());
                    userCookie.setMaxAge(3600 * 24);
                    response.sendRedirect("/users");
                }
                else{
                    response.sendRedirect("/signIn");
                }
            }
            else{
                response.sendRedirect("/signIn");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}