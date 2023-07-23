package com.geekster.expenseTrackerAPI.controller;

import com.geekster.expenseTrackerAPI.model.Expense;
import com.geekster.expenseTrackerAPI.model.User;
import com.geekster.expenseTrackerAPI.service.AuthenticationService;
import com.geekster.expenseTrackerAPI.service.ExpenseService;
import com.geekster.expenseTrackerAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@Validated
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping
    public String addExpense(@Valid @RequestBody Expense expense, @Valid @RequestParam  String email, @RequestParam String token) {
        if(authenticationService.authenticate(email, token)) {
            return expenseService.createExpense(expense,email);
        }else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @GetMapping
    public Object getExpensesByDate(@Valid @RequestParam String email, @RequestParam String token ,@RequestParam LocalDate date) {
        User user = userService.getUserByEmail(email);
        if(authenticationService.authenticate(email, token)) {
            return expenseService.getExpensesByDate(user, date);
        }
        return "Not an Authenticated user activity!!!";
    }

    @GetMapping("/total")
    public String getTotalExpenditureForMonth(@Valid @RequestParam String email, @RequestParam String token, @RequestParam int year, @RequestParam int month) {
        User user = userService.getUserByEmail(email);
        if(authenticationService.authenticate(email, token)) {
            return expenseService.getTotalExpenditureForMonth(user, year, month);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @GetMapping("/lastThreeMonths")
    public Object getExpensesForLastThreeMonths(@Valid @RequestParam String email, @RequestParam String token) {
        User user = userService.getUserByEmail(email);
        if(authenticationService.authenticate(email, token)) {
            return expenseService.getExpensesForLastThreeMonths(user);
        }
        return "Not an Authenticated user activity!!!";
    }

}
