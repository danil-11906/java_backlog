package functionals;

import interfaces.UsersRepository;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

// Светлана Михайловна Николай Вячеславович
public class UsersRepositoryJdbcImpl implements UsersRepository {

    private PasswordEncoder passwordEncoder;
    private DataSource dataSource;

    private static final String SQL_INSERT_INTO_USERS = "insert into users(login,password,name,surname) values (?,?,?,?)";
    private static final String SQL_SELECT_LOGIN_FROM_USERS = "select login,id,password from users where login=";
    private static final String SQL_SELECT_USER_FROM_USERS = "select login,name,surname from users where login=";
    private static final String SQL_SELECT_ID_USER = "select id from users where login=";

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void save(User entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_USERS, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, passwordEncoder.encode(entity.getPassword()));
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getSurname());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with insert user");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Problem");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(User login) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_LOGIN_FROM_USERS + "'" + login.getLogin() + "';");
            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .build();
                return Optional.ofNullable(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findUser(User login) {
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_USER_FROM_USERS + "'" + login.getLogin() + "';");
            while (resultSet.next()) {
                user = User.builder()
                        .login(resultSet.getString("login"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .build();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return user;
    }

    public User findId(String login) {
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID_USER + "'" + login + "';");
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .build();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return user;
    }
}