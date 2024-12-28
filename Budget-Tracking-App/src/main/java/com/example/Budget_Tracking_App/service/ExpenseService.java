package com.example.Budget_Tracking_App.service;

import com.example.Budget_Tracking_App.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    // Basic CRUD
    List<Expense> getExpenseList();
    void saveExpense(Expense expense);
    Expense getExpenseById(Long id);
    void updateExpense(Expense expense);
    void deleteExpenseById(Long id);

    // Pagination
    Page<Expense> getExpenseList(Pageable pageable);

    // Search and Filtering
    List<Expense> findExpensesByCategory(String category);
    List<Expense> findExpensesByDateRange(LocalDate startDate, LocalDate endDate);

    // Bulk Operations
    void saveAllExpenses(List<Expense> expenses);
    void deleteAllExpenses();
}