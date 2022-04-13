package com.example.carWorkshop.controller;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.repository.CarRepository;
import com.example.carWorkshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/cars")
public class CarController {

    final CarService carService;
    final CarRepository carRepository;

    public CarController(CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @RequestMapping(path = {"", "/search"})
    public String getCars(Car car, Model model, String keyword){
        if (keyword != null){
            List<Car> carListByKeyword = carService.getByKeyword(keyword);
            model.addAttribute("cars", carListByKeyword);
        }
        else {
            List<Car> carList = carRepository.findAll();
            model.addAttribute("cars", carList);
        }
        return "cars";
    }

    @GetMapping("/repair")
    public String repair(@RequestParam UUID carId){
        Car car = carService.load(carId);
        car.setRepairDate(LocalDate.now());
        car.setFixed(true);
        carService.save(car);
        carService.saveToFile();
        carService.saveRepairedCars();
        return "redirect:/cars";
    }
}
