package ru.habbit.tracker.service;

import org.springframework.stereotype.Service;
import ru.habbit.tracker.dto.HabitDto;
import ru.habbit.tracker.model.Habit;

import java.util.List;

@Service
public interface HabitService {

    List<Habit> getAllHabits(String nameHabit, Double duration);

    Habit getHabitById(long id);

    void addHabit(Habit model);

    void deleteHabit(long id);

    void updateHabit(Habit habit);
}
