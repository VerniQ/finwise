package me.verni.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseResponseDto {
    private Long id;
    private String title;
    private BigDecimal amount;
    private String categoryName;
    private LocalDateTime expenseDateTime;

    public ExpenseResponseDto(Long id, String title, BigDecimal amount, String categoryName, LocalDateTime expenseDateTime) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.categoryName = categoryName;
        this.expenseDateTime = expenseDateTime;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public BigDecimal getAmount() { return amount; }
    public String getCategoryName() { return categoryName; }
    public LocalDateTime getExpenseDateTime() { return expenseDateTime; }
}
