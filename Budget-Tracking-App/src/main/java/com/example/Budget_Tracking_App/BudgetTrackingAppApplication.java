package com.example.Budget_Tracking_App;

import com.example.Budget_Tracking_App.entity.Expense;
import com.example.Budget_Tracking_App.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class BudgetTrackingAppApplication implements CommandLineRunner {
	@Autowired
	private ExpenseRepository expenseRepository;

	private final Logger logger = LoggerFactory.getLogger(BudgetTrackingAppApplication.class);

    public BudgetTrackingAppApplication() {

    }

    public static void main(String[] args) {

		SpringApplication.run(BudgetTrackingAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Log query results
		Optional<Expense> existingExpense = expenseRepository.findByTitleIgnoreCase("Rent");
		if (existingExpense.isPresent()) {
			logger.info("Found existing expense: {}", existingExpense.get());
		} else {
			logger.info("No existing expense found with title 'Rent'.");
		}

		// Create a new expense
		Expense expense = new Expense(
				"Living expense",
				2500,
				"rent",
				"Every 1st of the month",
				LocalDate.now()
		);

		// Check for duplicates before saving
		if (expenseRepository.existsByTitleIgnoreCase(expense.getTitle())) {
			logger.warn("Expense with title '{}' already exists. Skipping save.", expense.getTitle());
		} else {
			expenseRepository.save(expense);
			logger.info("New expense saved: {}", expense);
		}
	}

}