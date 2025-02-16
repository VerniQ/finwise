package me.verni.expense;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
