package ru.habbit.tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.habbit.tracker.dto.HabitDto;
import ru.habbit.tracker.mapper.UserMapper;
import ru.habbit.tracker.model.Habit;
import ru.habbit.tracker.service.HabitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Habit-Tracker", description = "API для работы с привычками")
public class RestHabitController {

    private final HabitService habitService;
    private final UserMapper userMapper;

    @GetMapping
    @Operation(summary = "Получение всех привычек с базы", description = "Получение всех привычек по параметрам name, duration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычки получены", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            })
    })
    public ResponseEntity<List<Habit>> getAllHabits(){
        List<Habit> habits = habitService.getAllHabits();

        return ResponseEntity.ok(habits);
    }

}
