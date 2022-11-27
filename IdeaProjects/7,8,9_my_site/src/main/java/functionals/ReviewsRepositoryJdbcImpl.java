package functionals;

import interfaces.ReviewsRepository;
import models.Reviews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;


public class ReviewsRepositoryJdbcImpl implements ReviewsRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into reviews(id,text) " +
            "values (?, ?)";

    //language=SQL
    private final static String SQL_SELECT_ALL = "select * from reviews";

    private JdbcTemplate jdbcTemplate;

    public ReviewsRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Reviews> reviewsRowMapper = (row, rowNumber) -> Reviews.builder()
            .id(row.getLong("id"))
            .text(row.getString("text"))
            .build();

    public void save(Long id, Reviews entity) {
        jdbcTemplate.update(SQL_INSERT, id,
                entity.getText());
    }

    @Override
    public List<Reviews> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, reviewsRowMapper);
    }
}
