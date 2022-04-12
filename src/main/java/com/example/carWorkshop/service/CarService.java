package com.example.carWorkshop.service;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


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

    public Car load(UUID id) {
        return carRepository.findById(id).get();
    }

    public void saveToFile() {
        List<Car> cars = carRepository.findAll();
        try {
           objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/carsdata/cars.json"), cars);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveRepairedCars(){
        List<Car> repairedCars = carRepository.findAllByRepairDate(LocalDate.now());
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/carsdata/fixed/" + LocalDate.now()), repairedCars);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
