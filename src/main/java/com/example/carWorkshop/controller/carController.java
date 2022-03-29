package com.example.carWorkshop.controller;

import com.example.carWorkshop.repository.CarRepository;
import com.example.carWorkshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class carController {

    final
    CarService carService;
    CarRepository carRepository;

    public carController(CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String car(){
        return "home";
    }

    @GetMapping("/show")
    public ModelAndView getAllCars(){
        ModelAndView mav = new ModelAndView("cars");
        mav.addObject("cars", carRepository.findAll());
        return mav;
    }
}
