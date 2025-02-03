package me.verni.budget;

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
