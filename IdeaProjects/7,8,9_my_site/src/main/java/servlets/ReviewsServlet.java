package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.ReviewsRepository;
import interfaces.UsersRepository;
import models.Reviews;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewsServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    private UsersRepository usersRepository;
    private ReviewsRepository reviewsRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
        reviewsRepository = (ReviewsRepository) config.getServletContext().getAttribute("reviewsRepository");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Reviews> reviews = reviewsRepository.findAll();
        req.setAttribute("reviewsForJsp",reviews);
        req.getRequestDispatcher("/jsp/reviews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if ((Boolean) AutorityServlet.httpSession.getAttribute("authenticated")) {
                String cookie = AutorityServlet.cookie.getValue();
                Long id = usersRepository.findId(cookie).getId();
                // приняли JSON на вход, с помоьщью ObjectMapper-а превратили в User-объект
                Reviews review = Reviews.builder().text(req.getParameter("text")).build();
                // сохранили нового пользователя в бд
                reviewsRepository.save(id, review);
                resp.sendRedirect("/reviews");
            }
            else {
                resp.sendRedirect("/signIn");
            }
        } catch (NullPointerException e) {
            resp.sendRedirect("/signIn");
        }
//        // получили всех пользователей из бд
//        // сформировали JSON-строку-ответ
//        String reviewsAsJson = objectMapper.writeValueAsString(reviews);
//        resp.setContentType("application/json");
//        // отправили ответ
//        resp.getWriter().println(reviewsAsJson);
    }
}

