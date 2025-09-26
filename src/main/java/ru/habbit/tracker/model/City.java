package ru.habbit.tracker.model;


import jakarta.persistence.*;

@Entity
@Table(name = "cities", schema = "tracker")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String name;
}
