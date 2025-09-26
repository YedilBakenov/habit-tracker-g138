package ru.habbit.tracker.controller;

import io.swagger.v3.core.util.ReflectionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.ReflectionUtil;
import org.aspectj.util.Reflection;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.habbit.tracker.dto.ErrorHabitDto;
import ru.habbit.tracker.dto.HabitDto;
import ru.habbit.tracker.mapper.HabitMapper;
import ru.habbit.tracker.mapper.UserMapper;
import ru.habbit.tracker.model.Habit;
import ru.habbit.tracker.service.HabitService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Habit-Tracker", description = "API для работы с привычками")
public class RestHabitController {

    private final HabitService habitService;
    private final UserMapper userMapper;
    private final HabitMapper habitMapper;

    @GetMapping
    @Operation(summary = "Получение всех привычек с базы", description = "Получение всех привычек по параметрам name, duration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычки получены", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос данных", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 400,
                                        "title": "PROBLEM FORMAT",
                                        "detail": "INCORRECT FORMAT"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 500,
                                        "title": "INTERNAL ERROR",
                                        "detail": "SERVER ERROR"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class)
                    )
            })
    })
    public ResponseEntity<List<HabitDto>> getAllHabits(@RequestParam(required = false) String habitName,
                                                    @RequestParam(required = false) Double duration) {
        List<HabitDto> habitDtoList = habitMapper.toListDto(habitService.getAllHabits(habitName, duration));

        return ResponseEntity.ok(habitDtoList);
    }


    @GetMapping(value = "/{id}")
    @Operation(summary = "Получение одной привычки", description = "Получение привычки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка получена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверный запрос данных", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 400,
                                        "title": "PROBLEM FORMAT",
                                        "detail": "INCORRECT FORMAT"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "title": "HABIT NOT FOUND",
                                        "detail": "OBJECT NOT FOUND"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 500,
                                        "title": "INTERNAL ERROR",
                                        "detail": "SERVER ERROR"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class)
                    )
            })
    })
    public ResponseEntity<HabitDto> getHabitById(@PathVariable long id){

        HabitDto habitDto = habitMapper.toDto(habitService.getHabitById(id));

        return ResponseEntity.ok(habitDto);
    }



    @PostMapping
    @Operation(summary = "Добавление привычки", description = "Привычка добавлена в базу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка сохранена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверные данные", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 400,
                                        "title": "PROBLEM FORMAT",
                                        "detail": "INCORRECT FORMAT"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "title": "HABIT NOT FOUND",
                                        "detail": "OBJECT NOT FOUND"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 500,
                                        "title": "INTERNAL ERROR",
                                        "detail": "SERVER ERROR"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class)
                    )
            })
    })
    public ResponseEntity<HabitDto> addHabit(@Valid @RequestBody HabitDto habitDto){

        habitService.addHabit(habitMapper.toModel(habitDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(habitDto);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Удаление привычки", description = "Привычка удалена из базы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Привычка удалена"),
            @ApiResponse(responseCode = "400", description = "Неверные данные", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 400,
                                        "title": "VALIDATION FAILED",
                                        "detail": "INCORRECT DATA"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 500,
                                        "title": "INTERNAL ERROR",
                                        "detail": "SERVER ERROR"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class)
                    )
            })
    })
    public ResponseEntity<Void> deleteHabit(@PathVariable long id){
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    };


    @PutMapping
    @Operation(summary = "Обновление привычки", description = "Привычка обновлена в базе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Привычка обновлена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = HabitDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Неверные данные", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 400,
                                        "title": "PROBLEM FORMAT",
                                        "detail": "INCORRECT FORMAT"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Привычка не найдена", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 404,
                                        "title": "HABIT NOT FOUND",
                                        "detail": "OBJECT NOT FOUND"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class))
            }),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка", content = {
                    @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                            examples = @ExampleObject(value = """
                                    {
                                        "status": 500,
                                        "title": "INTERNAL ERROR",
                                        "detail": "SERVER ERROR"
                                    }
                                    """),
                            schema = @Schema(implementation = ErrorHabitDto.class)
                    )
            })
    })
    public ResponseEntity<HabitDto> updateHabit(@RequestBody HabitDto habitDto){

                habitService.updateHabit(habitMapper.toModel(habitDto));

            return ResponseEntity.ok(habitDto);
    };

}
