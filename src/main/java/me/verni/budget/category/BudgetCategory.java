package me.verni.budget.category;

import jakarta.persistence.*;
import me.verni.budget.general.Budget;
import me.verni.category.Category;

import java.math.BigDecimal;

@Entity
@Table(name = "budget_categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"budgetId", "categoryId"})
})
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetId", nullable = false)
    private Budget budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal budgetLimit;


    public BudgetCategory() {}

    public BudgetCategory(Budget budget, Category category, BigDecimal budgetLimit) {
        this.budget = budget;
        this.category = category;
        this.budgetLimit = budgetLimit;
    }

    // Gettery i Settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(BigDecimal budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

}