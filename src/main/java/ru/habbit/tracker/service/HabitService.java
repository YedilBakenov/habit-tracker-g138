package ru.habbit.tracker.service;

import org.springframework.stereotype.Service;
import ru.habbit.tracker.model.Habit;

import java.util.List;

@Service
public interface HabitService {

    List<Habit> getAllHabits();
}
