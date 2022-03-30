package com.example.carWorkshop.controller;

import com.example.carWorkshop.repository.CarRepository;
import com.example.carWorkshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


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
}
