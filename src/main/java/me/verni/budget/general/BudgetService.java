package me.verni.budget.general;

import jakarta.transaction.Transactional;
import me.verni.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Transactional
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> findByUser(User user) {
        return budgetRepository.findByUser(user);
    }

    public List<Budget> findByUserId(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    public List<Budget> findByUserIdAndMonth(Long userId, int month) {
        return budgetRepository.findByUserIdAndMonth(userId, month);
    }

    public List<Budget> findByUserIdAndYear(Long userId, int year) {
        return budgetRepository.findByUserIdAndYear(userId, year);
    }

    public List<Budget> findByUserIdAndMonthAndYear(Long userId, int month, int year) {
        return budgetRepository.findByUserIdAndMonthAndYear(userId, month, year);
    }

    public List<Budget> getBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long budgetId) {
        return budgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException("Budget not found"));
    }
}