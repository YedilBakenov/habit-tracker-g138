package ru.habbit.tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema(name = "schemaHabit")
@Data
public class HabitDto {
    private long id;
    private String nameHabit;
    private double duration;
    private LocalDate add;
    private LocalDate update;
    private String meta;
}
