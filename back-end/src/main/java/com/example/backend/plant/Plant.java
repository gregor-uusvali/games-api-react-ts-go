package com.example.backend.plant;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "plants")
public class Plant {
    @Id
    @SequenceGenerator(
            name = "plant_sequence",
            sequenceName = "plant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plant_sequence"
    )
    private Long id;
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String image;
    @Column(length = 1000)
    private String instruction;


    private LocalDate date;

    public Plant() {
    }

    public Plant(String name,
                 String description,
                 String image,
                 String instruction,
                 LocalDate date) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.instruction = instruction;
        this.date = date;
    }

    public Plant(Long id,
                 String name,
                 String description,
                 String image,
                 String instruction,
                 LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.instruction = instruction;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", instruction='" + instruction + '\'' +
                ", date=" + date +
                '}';
    }
}
