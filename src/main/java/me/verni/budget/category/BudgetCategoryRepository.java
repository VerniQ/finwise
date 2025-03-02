package me.verni.budget.category;

import me.verni.budget.general.Budget;
import me.verni.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
    List<BudgetCategory> findByBudget(Budget budget);
    List<BudgetCategory> findByBudgetAndCategory(Budget budget, Category category);
}