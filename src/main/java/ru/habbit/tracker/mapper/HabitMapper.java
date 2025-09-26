package ru.habbit.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.habbit.tracker.dto.HabitDto;
import ru.habbit.tracker.dto.UserDto;
import ru.habbit.tracker.model.Habit;
import ru.habbit.tracker.model.User;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface HabitMapper {

    @Mapping(source = "addHabit", target = "add")
    @Mapping(source = "updateHabit", target = "update")
    HabitDto toDto(Habit habit);

    @Mapping(source = "add", target = "addHabit")
    @Mapping(source = "update", target = "updateHabit")
    Habit toModel(HabitDto habitDto);

    List<HabitDto> toListDto(List<Habit> habits);
    List<Habit>toListModel(List<HabitDto> habitDtoList);
}
