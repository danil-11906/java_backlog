package functionals;

import interfaces.UsersRepository;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Светлана Михайловна Николай Вячеславович
public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;
    private Statement statement;
    private static final String SQL_INSERT_INTO_USERS = "insert into users(login,password,name,surname) values ";
    private static final String SQL_SELECT_ALL_FROM_USERS = "select * from users order by id";
    private static final String SQL_SELECT_LOGIN_FROM_USERS = "select login,id,password from users where login=";

    public UsersRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.statement = statement;
        this.connection = connection;
    }

    public void save(User entity) {
        String sql = SQL_INSERT_INTO_USERS + "('" + entity.getLogin() + "', '" + entity.getPassword() + "', '" + entity.getName() + "', '" +entity.getSurname() + "');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        System.out.println(entity.getLogin() + " " + entity.getPassword() + " " + entity.getName() + " " + entity.getSurname());
    }


    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_USERS);

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .build();
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(User login) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_LOGIN_FROM_USERS + "'" + login.getLogin() + "';");
            if(resultSet.next()){
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .build();
                return Optional.ofNullable(user);
            }
            else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
