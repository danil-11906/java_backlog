package servlets;

import functionals.UsersRepositoryJdbcImpl;
import interfaces.UsersRepository;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qawsedrf102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-906";

    private UsersRepository usersRepository;

    private List<User> users;
    
    @Override
    public void init() throws ServletException {
//        users = new ArrayList<User>();
//        User user1 = User.builder()
//                .id(8L)
//                .firstName("Oleg")
//                .lastName("Romanov")
//                .age(19)
//                .build();
//
//        User user2 = User.builder()
//                .id(9L)
//                .firstName("Danis")
//                .lastName("Zinnatullin")
//                .age(19)
//                .build();
//
//        users.add(user1);
//        users.add(user2);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersRepository = new UsersRepositoryJdbcImpl(connection, statement);
        } catch (SQLException throwables) {
            throw  new IllegalStateException(throwables);
        }
        users = usersRepository.findAll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter writer = response.getWriter();
//
//        StringBuilder resultHtml = new StringBuilder();
//        resultHtml.append("<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Users</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h1>Users</h1>\n" +
//                "<div>\n" +
//                "    <table>\n" +
//                "        <tr>\n" +
//                "            <th>ID</th>\n" +
//                "            <th>FIRST NAME</th>\n" +
//                "            <th>LAST NAME</th>\n" +
//                "            <th>AGE</th>\n" +
//                "        </tr>\n");
//        for (User user : users) {
//            resultHtml.append("<tr>\n");
//            resultHtml.append("<td>").append(user.getId()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getFirstName()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getLastName()).append("</td>\n");
//            resultHtml.append("<td>").append(user.getAge()).append("</td>\n");
//            resultHtml.append("<tr>");
//        }
//        resultHtml.append("    </table>\n" +
//                "</div>\n" +
//                "</body>\n" +
//                "</html>");
//        writer.write(resultHtml.toString());
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request,response);
    }
}
