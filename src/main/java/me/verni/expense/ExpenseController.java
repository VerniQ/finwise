package me.verni.expense;

import me.verni.category.Category;
import me.verni.category.CategoryService;
import me.verni.expense.dto.ExpenseDto;
import me.verni.expense.dto.ExpenseResponseDto;
import me.verni.user.User;
import me.verni.user.UserService;
import me.verni.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final JwtUtil jwtUtil;

    public ExpenseController(ExpenseService expenseService, UserService userService, CategoryService categoryService, JwtUtil jwtUtil) {
        this.expenseService = expenseService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseResponseDto>> getExpensesByUser(@PathVariable Long userId) {
        List<Expense> expenses = expenseService.findByUserId(userId);

        List<ExpenseResponseDto> responseDtos = expenses.stream()
                .map(expense -> new ExpenseResponseDto(
                        expense.getExpenseId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getCategory().getName(),
                        expense.getExpenseDate()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<Expense> saveExpense(@RequestBody ExpenseDto expenseDto, @RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryService.getCategoryById(expenseDto.getCategoryId());

        Expense expense = new Expense(
                user,
                category,
                expenseDto.getExpenseDateTime(),
                expenseDto.getAmount(),
                expenseDto.getTitle()
        );

        Expense savedExpense = expenseService.saveExpense(expense);
        return ResponseEntity.status(201).body(savedExpense);
    }
}
