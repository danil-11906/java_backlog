package interfaces;

import models.User;

import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    Optional<T> findByLogin(T login);
    User findUser(User login);
    User findId(String login);
}
