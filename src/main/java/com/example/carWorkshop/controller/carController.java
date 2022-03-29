package com.example.carWorkshop.controller;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class carController {

    final
    CarRepository carRepository;

    public carController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String car(){
        carRepository.deleteAll();
        Car car = new Car("name1", "GA 01001", "Audi", "red", LocalDate.now());
        carRepository.save(car);
        return "home";
    }

    @GetMapping("/show")
    public ModelAndView getAllCars(){
        ModelAndView mav = new ModelAndView("cars");
        mav.addObject("cars", carRepository.findAll());
        return mav;
    }
}
