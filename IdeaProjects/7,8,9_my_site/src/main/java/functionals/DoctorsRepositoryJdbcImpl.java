package functionals;

import interfaces.DoctorsRepository;
import models.Doctors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class DoctorsRepositoryJdbcImpl implements DoctorsRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into doctors(name, secondName, lastName, position, exp) " +
            "values (?, ?, ?, ?, ?)";

    //language=SQL
    private final static String SQL_SELECT_ALL = "select * from doctors";

    private JdbcTemplate jdbcTemplate;

    public DoctorsRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Doctors> doctorsRowMapper = (row, rowNumber) -> Doctors.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .secondName(row.getString("secondName"))
            .lastName(row.getString("lastName"))
            .position(row.getString("position"))
            .exp(row.getString("exp"))
            .build();


    @Override
    public void insert(Doctors entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getName(), entity.getSecondName(), entity.getLastName(), entity.getPosition(), entity.getExp() + " years");
    }

    @Override
    public List<Doctors> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, doctorsRowMapper);
    }
}