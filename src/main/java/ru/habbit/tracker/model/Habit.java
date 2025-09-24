package ru.habbit.tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "habits", schema = "tracker")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
