package com.geekster.expenseTrackerAPI.repository;

import com.geekster.expenseTrackerAPI.model.Expense;
import com.geekster.expenseTrackerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense,Long> {
    List<Expense> findAllByUserAndDate(User user, LocalDate date);

    List<Expense> findAllByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
