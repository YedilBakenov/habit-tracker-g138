package ru.habbit.tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.habbit.tracker.model.City;
import ru.habbit.tracker.model.Habit;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(name = "UserSchema")
public class UserDto {
    private long id;
    private String name;
    private String fullName;
    private int age;
    private List<City> cities;
    private List <Habit> habits;
    private LocalDate create;
    private LocalDate update;
}
