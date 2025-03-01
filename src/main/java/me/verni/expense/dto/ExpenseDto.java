package me.verni.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseDto {
    private String title;
    private Long categoryId;
    private LocalDateTime expenseDateTime;
    private BigDecimal amount;

    public ExpenseDto() {}

    public ExpenseDto(String title, Long categoryId, LocalDateTime expenseDateTime, BigDecimal amount) {
        this.title = title;
        this.categoryId = categoryId;
        this.expenseDateTime = expenseDateTime;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getExpenseDateTime() {
        return expenseDateTime;
    }

    public void setExpenseDateTime(LocalDateTime expenseDateTime) {
        this.expenseDateTime = expenseDateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
