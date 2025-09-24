package ru.habbit.tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.habbit.tracker.model.Habit;
import ru.habbit.tracker.repository.HabitRepository;
import ru.habbit.tracker.service.HabitService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    @Override
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }
}
