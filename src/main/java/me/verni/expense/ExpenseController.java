package me.verni.expense;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getExpenses(){
        return expenseService.getExpenses();
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(Long userId) {
        return expenseService.findByUserId(userId);
    }

    @PostMapping
    public Expense saveExpense(Expense expense) {
        return expenseService.saveExpense(expense);
    }
}
