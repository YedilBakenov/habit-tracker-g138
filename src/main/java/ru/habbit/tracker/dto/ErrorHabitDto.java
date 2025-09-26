package ru.habbit.tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "schemaErrorHabit")
public class ErrorHabitDto {

    @Schema(example = "400", description = "Код ошибки")
    private int status;

    @Schema(example = "Неверные формат данных", description = "Краткое описание ошибки")
    private String title;

    @Schema(example = "Поле id неверное", description = "Подробности ошибки")
    private String detail;
}
