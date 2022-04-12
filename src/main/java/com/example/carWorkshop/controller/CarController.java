package com.example.carWorkshop.controller;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.repository.CarRepository;
import com.example.carWorkshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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

    @GetMapping("")
    public ModelAndView getAllCars(){
        ModelAndView mav = new ModelAndView("cars");
        mav.addObject("cars", carRepository.findAll());
        return mav;
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
