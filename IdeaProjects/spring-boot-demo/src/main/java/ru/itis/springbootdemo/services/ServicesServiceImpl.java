package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.*;
import ru.itis.springbootdemo.models.Bag;
import ru.itis.springbootdemo.models.Service;
import ru.itis.springbootdemo.repositories.BagRepository;
import ru.itis.springbootdemo.repositories.ServicesRepository;

import java.util.List;

import static ru.itis.springbootdemo.dto.ServiceDto.from;
import static ru.itis.springbootdemo.dto.BagDto.from;

@Component
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private BagRepository bagRepository;

    @Override
    public void addForBagService(ServiceDto serviceDto, Long id) {
        Bag bag = Bag.builder()
                .name(serviceDto.getName())
                .cost(serviceDto.getCost())
                .user_id(id)
                .build();
        bagRepository.save(bag);
    }

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public List<BagDto> getServicesName() {
        return BagDto.from(servicesRepository.findAll());
    }

    @Override
    public List<ServiceDto> getAllServices() {
        return ServiceDto.from(servicesRepository.findAll());
    }

    @Override
    public ServiceDto addService(ServiceForm serviceForm) {
        Service service = Service.builder()
                .name(serviceForm.getName())
                .cost(serviceForm.getCost())
                .date(serviceForm.getDate())
                .build();
        servicesRepository.save(service);
        return ServiceDto.of(service);
    }

    @Override
    public List<ServiceDto> search(Integer size, Integer page, String query, String sortParameter, String directionParameter) {
        Sort.Direction direction = Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "id");

        if (directionParameter != null) {
            direction = Sort.Direction.fromString(directionParameter);
        }

        if (sortParameter != null) {
            sort = Sort.by(direction, sortParameter);
        }

        if (query == null) {
            query = "empty";
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Service> papersPage = servicesRepository.search(query, pageRequest);
        return ServiceDto.from(papersPage.getContent());
    }

    @Override
    public Long countService() {
        return servicesRepository.count();
    }
}