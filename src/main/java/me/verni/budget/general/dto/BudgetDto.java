package me.verni.budget.dto;

import java.math.BigDecimal;

public class BudgetDto {
    private Long userId;
    private BigDecimal budgetLimit;
    private int month;
    private int year;

    public BudgetDto() {}

    public BudgetDto(Long userId, BigDecimal budgetLimit, int month, int year) {
        this.userId = userId;
        this.budgetLimit = budgetLimit;
        this.month = month;
        this.year = year;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(BigDecimal budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}