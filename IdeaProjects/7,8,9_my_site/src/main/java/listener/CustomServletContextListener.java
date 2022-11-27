package listener;

import functionals.*;
import interfaces.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "qawsedrf102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-906";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext servletContext = sce.getServletContext();
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUsername(DB_USERNAME);
            dataSource.setPassword(DB_PASSWORD);
            dataSource.setUrl(DB_URL);
            UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
            SignUpService signUpService = new SignUpServiceImpl(usersRepository);
            SignInService signInService = new SignInServiceImpl(usersRepository);
            ServicesRepository servicesRepository = new ServicesRepositoryJdbcImpl(dataSource);
            ReviewsRepository reviewsRepository = new ReviewsRepositoryJdbcImpl(dataSource);
            DoctorsRepository doctorsRepository = new DoctorsRepositoryJdbcImpl(dataSource);
            FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);
            FilesService filesUploadService = new FilesServiceImpl(filesRepository);


            servletContext.setAttribute("signUpService", signUpService);
            servletContext.setAttribute("signInService", signInService);
            servletContext.setAttribute("usersRepository",usersRepository);
            servletContext.setAttribute("servicesRepository",servicesRepository);
            servletContext.setAttribute("reviewsRepository", reviewsRepository);
            servletContext.setAttribute("doctorsRepository", doctorsRepository);
            servletContext.setAttribute("filesUploadService", filesUploadService);


        } catch (SQLException e) {
            throw new IllegalArgumentException("proverka");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
