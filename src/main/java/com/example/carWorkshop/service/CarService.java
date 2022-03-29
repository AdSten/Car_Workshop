package com.example.carWorkshop.service;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class CarService {

    final CarRepository carRepository;
    final ObjectMapper objectMapper;
    public CarService(CarRepository carRepository, ObjectMapper objectMapper) {
        this.carRepository = carRepository;
        this.objectMapper = objectMapper;
    }

    public Car save(Car car){
        return carRepository.save(car);
    }

    public Iterable<Car> save(List<Car> cars) {
        return carRepository.saveAll(cars);
    }

    public Car load(String name) {
        return carRepository.findByName(name);
    }

    public void saveToFile() {
        List<Car> cars = carRepository.findAll();
        try {
           objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/carsdata/cars.json"), cars);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
