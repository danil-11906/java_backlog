package interfaces;

import models.Reviews;

import java.util.List;

public interface ReviewsRepository {
    void save(Long id, Reviews entity);
    List<Reviews> findAll();
}
