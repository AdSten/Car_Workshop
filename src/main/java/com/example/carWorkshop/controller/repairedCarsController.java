package com.example.carWorkshop.controller;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.repository.CarRepository;
import com.example.carWorkshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/repaired")
public class repairedCarsController {

    final CarService carService;
    final CarRepository carRepository;

    public repairedCarsController(CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @RequestMapping(path = {"", "/search"})
    public String getCars(Car car, Model model, String keyword){
        if (keyword != null){
            List<Car> repairedCarListByKeyword = carService.getByKeyword(keyword);
            model.addAttribute("repairedCars", repairedCarListByKeyword);
        }
        else {
            List<Car> repairedCarList = carRepository.findAll();
            model.addAttribute("repairedCars", repairedCarList);
        }
        return "repairedCars";
    }
}
