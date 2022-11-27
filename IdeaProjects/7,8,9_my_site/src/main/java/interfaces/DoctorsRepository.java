package interfaces;

import models.Doctors;

import java.util.List;

public interface DoctorsRepository {
    void insert(Doctors entity);
    public List<Doctors> findAll();
}
