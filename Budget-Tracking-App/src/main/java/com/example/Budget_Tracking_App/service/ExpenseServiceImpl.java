package com.example.Budget_Tracking_App.service;

import com.example.Budget_Tracking_App.entity.Expense;
import com.example.Budget_Tracking_App.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getExpenseList() {
        logger.info("Fetching all expenses...");
        return expenseRepository.findAll();
    }

    @Override
    public void saveExpense(Expense expense) {
        logger.info("Saving expense: {}", expense);
        expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(Long id) {
        logger.info("Fetching expense with ID: {}", id);
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with ID " + id + " not found!"));
    }

    @Override
    @Transactional
    public void updateExpense(Expense expense) {
        logger.info("Updating expense with ID: {}", expense.getId());
        Expense oldExpense = getExpenseById(expense.getId());

        // Merge changes (if needed) to prevent overwriting fields unintentionally
        if (expense.getCategory() != null) oldExpense.setCategory(expense.getCategory());
        if (expense.getAmount() != 0) oldExpense.setAmount(expense.getAmount());
        if (expense.getTitle() != null) oldExpense.setTitle(expense.getTitle());
        if (expense.getDescription() != null) oldExpense.setDescription(expense.getDescription());
        if (expense.getDate() != null) oldExpense.setDate(expense.getDate());

        expenseRepository.save(oldExpense);
        logger.info("Expense updated successfully: {}", oldExpense);
    }

    @Override
    public void deleteExpenseById(Long id) {
        logger.info("Deleting expense with ID: {}", id);
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            logger.info("Expense with ID {} deleted successfully.", id);
        } else {
            throw new EntityNotFoundException("Expense with ID " + id + " does not exist");
        }
    }

    @Override
    public Page<Expense> getExpenseList(Pageable pageable) {
        return null;
    }

    @Override
    public List<Expense> findExpensesByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Expense> findExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public void saveAllExpenses(List<Expense> expenses) {

    }

    @Override
    public void deleteAllExpenses() {

    }
}