package com.example.carWorkshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = Car.TABLE_NAME)
@NoArgsConstructor
@Setter
@Getter
public class Car {

    public static final String TABLE_NAME = "car";
    public static final String COLUMN_PREFIX = "c_";

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = COLUMN_PREFIX + "id")
    private UUID id;

    @Column(name = COLUMN_PREFIX + "name", nullable = false)
    private String name;

    @Column(name = COLUMN_PREFIX + "registerNumber", nullable = false, unique = true)
    private String registerNumber;

    @Column(name = COLUMN_PREFIX + "model", nullable = false)
    private String model;

    @Column(name = COLUMN_PREFIX + "colour", nullable = false)
    private String colour;

    @Column(name = COLUMN_PREFIX + "registryDate", nullable = false)
    private LocalDate registryDate;

    @Column(name = COLUMN_PREFIX + "isFixed", nullable = false)
    private boolean isFixed;

    public Car(String name, String registerNumber, String model, String colour, LocalDate registryDate) {
        this.name = name;
        this.registerNumber = registerNumber;
        this.model = model;
        this.colour = colour;
        this.registryDate = registryDate;
        this.isFixed = false;
    }
}
