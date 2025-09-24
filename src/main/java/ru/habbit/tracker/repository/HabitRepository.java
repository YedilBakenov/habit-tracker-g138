package ru.habbit.tracker.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.habbit.tracker.model.Habit;

@Repository
@Transactional
public interface HabitRepository extends JpaRepository<Habit, Long> {
}
