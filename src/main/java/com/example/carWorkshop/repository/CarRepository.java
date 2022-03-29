package com.example.carWorkshop.repository;

import com.example.carWorkshop.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
