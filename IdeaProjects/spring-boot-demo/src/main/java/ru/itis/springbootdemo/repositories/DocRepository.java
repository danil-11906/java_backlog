package ru.itis.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springbootdemo.models.Doctors;

import java.util.Optional;

public interface DocRepository extends JpaRepository<Doctors, Long> {
    Optional<Doctors> findById(Long Id);
}
