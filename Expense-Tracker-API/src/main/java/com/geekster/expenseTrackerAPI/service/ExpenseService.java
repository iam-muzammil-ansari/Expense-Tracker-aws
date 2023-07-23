package com.geekster.expenseTrackerAPI.service;

import com.geekster.expenseTrackerAPI.model.Expense;
import com.geekster.expenseTrackerAPI.model.User;
import com.geekster.expenseTrackerAPI.repository.ExpenseRepo;
import com.geekster.expenseTrackerAPI.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepo expenseRepo;

    @Autowired
    UserRepo userRepo;
    public String createExpense(Expense expense, String email) {
        User expenseOwner = userRepo.findFirstByEmail(email);
        if(expenseOwner != null) {
            expense.setUser(expenseOwner);
            expenseRepo.save(expense);
            return "expense Created!!!";
        }else {
            return "User not found!";
        }
    }

    public List<Expense> getExpensesByDate(User user, LocalDate date) {
        // Implement expense retrieval logic here
        return expenseRepo.findAllByUserAndDate(user, date);
    }

    public String getTotalExpenditureForMonth(User user, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Expense> expenses = expenseRepo.findAllByUserAndDateBetween(user, startDate, endDate);
        if(expenses!= null){
            return "total expenditure of month "+month+" of year "+year+" is "+expenses.stream().mapToDouble(Expense::getPrice).sum();
        }
        return null;
    }

    public List<Expense> getExpensesForLastThreeMonths(User user) {
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgoDate = currentDate.minusMonths(3);

        // Validate if the user has expenses for at least three months
        if (userHasExpensesForAtLeastThreeMonths(user, threeMonthsAgoDate, currentDate)) {
            return expenseRepo.findAllByUserAndDateBetween(user, threeMonthsAgoDate, currentDate);
        } else {
            // Return an empty list if the user doesn't have expenses for three months
            return List.of();
        }
    }

    private boolean userHasExpensesForAtLeastThreeMonths(User user, LocalDate startDate, LocalDate endDate) {
        List<Expense> expenses = expenseRepo.findAllByUserAndDateBetween(user, startDate, endDate);
        int totalMonths = calculateTotalMonths(startDate, endDate);
        return expenses.size() > totalMonths;
    }

    private int calculateTotalMonths(LocalDate startDate, LocalDate endDate) {
        int startYear = startDate.getYear();
        int startMonth = startDate.getMonthValue();
        int endYear = endDate.getYear();
        int endMonth = endDate.getMonthValue();

        return (endYear - startYear) * 12 + (endMonth - startMonth) + 1;
    }

}
