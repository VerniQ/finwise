package me.verni.budget;

import me.verni.user.User;

import java.util.List;

public class BudgetService {
    private BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget saveBudget(Budget budget){
        return budgetRepository.save(budget);
    }

    public List<Budget> findByUser(User user){
        return budgetRepository.findByUser(user);
    }

    public List<Budget> findByUserId(Long userId){
        return budgetRepository.findByUserId(userId);
    }

    public List<Budget> getBudgets(){
        return budgetRepository.findAll();
    }
}
