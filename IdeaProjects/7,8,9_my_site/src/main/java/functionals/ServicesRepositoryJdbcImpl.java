package functionals;

import interfaces.ServicesRepository;
import models.Service;

import javax.sql.DataSource;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServicesRepositoryJdbcImpl implements ServicesRepository {

    private DataSource dataSource;

    private static final String SQL_INSERT_INTO_SERVICES = "insert into services(name,cost,date) values (?,?,?)";
    private static final String SQL_SELECT_ALL_FROM_SERVICES = "select * from services order by id";
    private static final String SQL_INSERT_INTO_CALCULATOR = "insert into calculator(id,name,cost) values (?,?,?)";
    private static final String SQL_SELECT_COST_FROM_SERVICES = "select cost from services where name=";
    private static final String SQL_SELECT_ID_FROM_CALCULATOR = "select id from calculator where id=";
    private static final String SQL_SELECT_CALCULATOR = "delete from calculator where id=";

    public ServicesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }
    @Override
    public void insert(Service entity) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_SERVICES, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCost() + " rub");
            statement.setString(3, entity.getDate());
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

    public List<Service> findAll() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_SERVICES);

            List<Service> result = new ArrayList<>();

            while (resultSet.next()) {
                Service service = Service.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .cost(resultSet.getString("cost"))
                        .date(resultSet.getString("date"))
                        .build();
                result.add(service);
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String findCost(String name) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_COST_FROM_SERVICES + "'" + name + "';");
            String cost = null;
            while (resultSet.next()) {
                cost = resultSet.getString("cost");
            }
            return cost;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void insertOrder(Long id, List<Service> list) {
        try {
            int affectedRows = 0;
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_CALCULATOR);
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(SQL_SELECT_ID_FROM_CALCULATOR + "'" + id + "';");
            while (resultSet.next()){
                statement1.executeUpdate(SQL_SELECT_CALCULATOR + "'" + id + "';");
            }
            for (int i = 0; i < list.size(); i++) {
                statement.setLong(1, id);
                statement.setString(2, list.get(i).getName());
                statement.setString(3, list.get(i).getCost());
                affectedRows = statement.executeUpdate();
            }
            if (affectedRows == 0) {
                throw new SQLException("Problem with insert user");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
