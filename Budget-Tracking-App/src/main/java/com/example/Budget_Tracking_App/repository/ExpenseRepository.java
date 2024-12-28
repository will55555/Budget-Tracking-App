package com.example.Budget_Tracking_App.repository;

import com.example.Budget_Tracking_App.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository  extends ListCrudRepository<Expense, Long>{
    Optional<Expense> findByTitle(String title);

    Optional<Expense> findByTitleIgnoreCase(String rent);

    boolean existsByTitleIgnoreCase(String title);
}