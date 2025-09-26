package ru.habbit.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "habits", schema = "tracker")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameHabit;
    private double duration;
    private LocalDate addHabit;
    private LocalDate updateHabit;
    private String meta;

    @PrePersist
    public void addHabit(){
        addHabit = LocalDate.now();
    }

    @PreUpdate
    public void updateHabit(){
        updateHabit  = LocalDate.now();
    }
}
