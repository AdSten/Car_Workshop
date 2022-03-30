package com.example.carWorkshop.controller;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/new")
public class NewCarController {


    final CarService carService;

    public NewCarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public String addNew(Model model){
        model.addAttribute("newCar", new Car());
        return "newCar";
    }

    @PostMapping("")
    public String addCar(@Valid @ModelAttribute("newCar") Car car, Errors errors) {
        if (errors.hasErrors()) {
            return "newCar";
        }
            car.setRegistryDate(LocalDate.now());
            try {
                carService.save(car);
                carService.saveToFile();
            } catch (Exception e){
                return "redirect:/new?error";
            }
            return "redirect:/new?done";
        }
    }
