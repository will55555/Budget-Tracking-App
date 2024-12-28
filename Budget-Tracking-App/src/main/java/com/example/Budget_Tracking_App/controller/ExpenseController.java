package com.example.Budget_Tracking_App.controller;

import com.example.Budget_Tracking_App.entity.Expense;
import com.example.Budget_Tracking_App.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @GetMapping("/expense/")
    public String getAllExpenses(Model model) {
        List<Expense> expenses = expenseService.getExpenseList();
        model.addAttribute("expenses", expenses);
        return "listOfExpenses";
    }

    @GetMapping("/expense/add")
    public String addToy(Model model) {
        model.addAttribute("expense", new Expense());
        return "addExpense";
    }

    @PostMapping("/expense/add")
    public String addExpense(@ModelAttribute("expense") @Valid Expense expense, Errors errors) {
        if(errors.hasErrors()) {
            return "addExpense";
        }
        expenseService.saveExpense(expense);
        return "redirect:/expense/";
    }

    @GetMapping("/expense/edit/{id}")
    public String updateToy(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "editExpense";
    }

    @PostMapping("/expense/edit/{id}")
    public String updateToy(@ModelAttribute("expense") @Valid Expense expense, Errors errors) {
        if(errors.hasErrors()){
            return "editExpense";
        }

        expenseService.updateExpense(expense);
        return "redirect:/expense/";
    }

    @GetMapping("/expense/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
        return "redirect:/expense/";
    }

}