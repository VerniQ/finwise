package me.verni.budget.general;

import me.verni.budget.dto.BudgetDto;
import me.verni.user.User;
import me.verni.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final UserService userService;

    public BudgetController(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getAllBudgets() {
        List<BudgetDto> budgetDtos = budgetService.getBudgets().stream()
                .map(budget -> new BudgetDto(budget.getUser().getId(), budget.getBudgetLimit(), budget.getMonth(), budget.getYear()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(budgetDtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetDto>> getBudgetsByUserId(@PathVariable Long userId) {
        List<BudgetDto> budgetDtos = budgetService.findByUserId(userId).stream()
                .map(budget -> new BudgetDto(budget.getUser().getId(), budget.getBudgetLimit(), budget.getMonth(), budget.getYear()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(budgetDtos);
    }

    @PostMapping
    public ResponseEntity<BudgetDto> createBudget(@RequestBody BudgetDto budgetDto) {
        System.out.println("Received DTO: " + budgetDto);  // Debug

        BigDecimal limit = budgetDto.getBudgetLimit() != null ? budgetDto.getBudgetLimit() : BigDecimal.ZERO;

        User user = userService.getUserById(budgetDto.getUserId());
        Budget budget = new Budget(user, limit, budgetDto.getMonth(), budgetDto.getYear());
        Budget savedBudget = budgetService.saveBudget(budget);

        BudgetDto responseDto = new BudgetDto(savedBudget.getUser().getId(), savedBudget.getBudgetLimit(), savedBudget.getMonth(), savedBudget.getYear());
        return ResponseEntity.status(201).body(responseDto);
    }


    @GetMapping("/user/{userId}/filter")
    public ResponseEntity<List<BudgetDto>> getBudgetsByUserFilter(@PathVariable Long userId,
                                                                  @RequestParam(required = false) Integer month,
                                                                  @RequestParam(required = false) Integer year) {
        List<Budget> budgets;
        if (month != null && year != null) {
            budgets = budgetService.findByUserIdAndMonthAndYear(userId, month, year);
        } else if (month != null) {
            budgets = budgetService.findByUserIdAndMonth(userId, month);
        } else if (year != null) {
            budgets = budgetService.findByUserIdAndYear(userId, year);
        } else {
            budgets = budgetService.findByUserId(userId);
        }

        List<BudgetDto> budgetDtos = budgets.stream()
                .map(budget -> new BudgetDto(budget.getUser().getId(), budget.getBudgetLimit(), budget.getMonth(), budget.getYear()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(budgetDtos);
    }

}
