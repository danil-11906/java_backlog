package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.BagDto;
import ru.itis.springbootdemo.dto.ServiceDto;
import ru.itis.springbootdemo.dto.ServiceForm;
import ru.itis.springbootdemo.models.Service;

import java.util.List;

public interface ServicesService {
    List<BagDto> getServicesName();
    List<ServiceDto> getAllServices();
    ServiceDto addService(ServiceForm serviceForm);
    List<ServiceDto> search(Integer size, Integer page, String query, String sort, String direction);
    Long countService();
    void addForBagService(ServiceDto serviceDto, Long id);

}
