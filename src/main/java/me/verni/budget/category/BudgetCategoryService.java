package me.verni.budget.category;

import jakarta.transaction.Transactional;
import me.verni.budget.general.Budget;
import me.verni.category.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetCategoryService {

    private final BudgetCategoryRepository budgetCategoryRepository;

    public BudgetCategoryService(BudgetCategoryRepository budgetCategoryRepository) {
        this.budgetCategoryRepository = budgetCategoryRepository;
    }

    @Transactional
    public BudgetCategory saveBudgetCategory(BudgetCategory budgetCategory) {
        return budgetCategoryRepository.save(budgetCategory);
    }

    public List<BudgetCategory> findByBudget(Budget budget) {
        return budgetCategoryRepository.findByBudget(budget);
    }

    public List<BudgetCategory> findByBudgetAndCategory(Budget budget, Category category) {
        return budgetCategoryRepository.findByBudgetAndCategory(budget, category);
    }

    public List<BudgetCategory> getAllBudgetCategories() {
        return budgetCategoryRepository.findAll();
    }
}