package interfaces;

import models.Service;

import java.util.List;

public interface ServicesRepository {
    void insert(Service entity);
    List<Service> findAll();
    void insertOrder(Long id,List<Service> list);
    String findCost(String name);
}
