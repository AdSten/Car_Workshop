package com.example.carWorkshop;

import com.example.carWorkshop.model.Car;
import com.example.carWorkshop.repository.CarRepository;
import com.example.carWorkshop.service.CarService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class CarWorkshopApplication {

	private static final Logger LOGGER = LogManager.getLogger(CarWorkshopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarWorkshopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CarService carService, CarRepository carRepository){
		return args -> {
			carRepository.deleteAll();
			ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
			TypeReference<List<Car>> carTypeReference = new TypeReference<>(){};
			InputStream carDataInputStream = TypeReference.class.getResourceAsStream("/carsdata/cars.json");
			try{
				List<Car> cars = mapper.readValue(carDataInputStream, carTypeReference);
				carService.save(cars);
			} catch (IOException e){
				LOGGER.fatal("Unable to save data to json file: " + e.getMessage());
			}
		};
	}

}
