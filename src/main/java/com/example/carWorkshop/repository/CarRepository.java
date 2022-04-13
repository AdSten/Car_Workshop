package com.example.carWorkshop.repository;

import com.example.carWorkshop.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
     List<Car> findAllByRepairDate(LocalDate localDate);
     @Query(value = "select * from car c where c_name like %:keyword% or c_register_number like %:keyword% or c_model like " +
             "%:keyword% or c_colour like %:keyword% or c_registry_date like %:keyword%" ,nativeQuery = true)
     List<Car> findAllByKeywordIgnoreCase(@Param("keyword") String keyword);
}
