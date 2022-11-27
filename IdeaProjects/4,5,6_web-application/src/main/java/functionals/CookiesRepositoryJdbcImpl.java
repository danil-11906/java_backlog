package functionals;

import interfaces.CookiesRepository;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CookiesRepositoryJdbcImpl implements CookiesRepository {

    private Connection connection;
    private Statement statement;
    private static final String SQL_INSERT_INTO_COOKIES = "insert into cookes(uuid,user_id) values ";
    private static final String SQL_SELECT_FROM_COOKIES = "select user_id from cookes where user_id =";
    private static final String SQL_UPDATE_COOKIES = "update cookes set uuid =";

    public CookiesRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public void save(Cookie cookie, Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_COOKIES + "'" + id + "';");
            String sql;
            if(resultSet.next()) {
                sql = SQL_UPDATE_COOKIES + "'" + cookie.getValue() + "'" + "where user_id=" + "'" + id + "'";
            }
            else {
                sql = SQL_INSERT_INTO_COOKIES + "('" + cookie.getValue() + "', '" + id + "');";
            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
