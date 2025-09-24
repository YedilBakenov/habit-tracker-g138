package ru.habbit.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users", schema = "tracker")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String fullName;
    private int age;

    @ManyToMany
    private List<City> cities;

    @ManyToMany
    private List <Habit> habits;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    public void createdAt(){
        createdAt = LocalDate.now();
    }

    @PostUpdate
    public void updatedAt(){
        updatedAt = LocalDate.now();
    }


}
