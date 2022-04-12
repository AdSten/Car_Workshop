package com.example.carWorkshop.repository;

import com.example.carWorkshop.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
     List<Car> findAllByRepairDate(LocalDate localDate);
}
