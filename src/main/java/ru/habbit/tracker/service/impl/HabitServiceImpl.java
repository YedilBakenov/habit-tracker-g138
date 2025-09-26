package ru.habbit.tracker.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.habbit.tracker.dto.HabitDto;
import ru.habbit.tracker.error.ModelNotFoundException;
import ru.habbit.tracker.model.Habit;
import ru.habbit.tracker.repository.HabitRepository;
import ru.habbit.tracker.service.HabitService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final EntityManager entityManager;

    @Override
    public List<Habit> getAllHabits(String nameHabit, Double duration) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Habit> query = cb.createQuery(Habit.class);
        Root<Habit> root = query.from(Habit.class);

        List<Predicate> predicates = new ArrayList<>();

        if(nameHabit!=null && !nameHabit.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("nameHabit")), nameHabit));
        }

        if(duration!=null){
            predicates.add(cb.equal(root.get("duration"), duration));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public Habit getHabitById(long id) {
        return habitRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Habit Not Found"));
    }

    @Override
    public void addHabit(Habit model) {
        habitRepository.save(model);
    }

    @Override
    public void deleteHabit(long id) {

        Habit habit = habitRepository.findById(id).orElseThrow(()-> new ModelNotFoundException("Habit Not Found"));

        habitRepository.deleteById(id);
    }

    public void updateHabit(Habit habit) {

        habitRepository.save(habit);
    }
}
