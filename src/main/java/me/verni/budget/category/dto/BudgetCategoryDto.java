package me.verni.budget.category.dto;

import java.math.BigDecimal;

public class BudgetCategoryDto {
    private Long budgetId;
    private Long categoryId;
    private BigDecimal budgetLimit;

    public BudgetCategoryDto() {}

    public BudgetCategoryDto(Long budgetId, Long categoryId, BigDecimal budgetLimit) {
        this.budgetId = budgetId;
        this.categoryId = categoryId;
        this.budgetLimit = budgetLimit;
    }

    // Gettery i Settery
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(BigDecimal budgetLimit) {
        this.budgetLimit = budgetLimit;
    }
}