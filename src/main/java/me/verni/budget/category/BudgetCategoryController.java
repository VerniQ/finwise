package me.verni.budget.category;

import me.verni.budget.category.dto.BudgetCategoryDto;
import me.verni.budget.general.Budget;
import me.verni.budget.general.BudgetService;
import me.verni.category.Category;
import me.verni.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budget-categories")
public class BudgetCategoryController {

    private final BudgetCategoryService budgetCategoryService;
    private final BudgetService budgetService;
    private final CategoryService categoryService;

    public BudgetCategoryController(BudgetCategoryService budgetCategoryService, BudgetService budgetService, CategoryService categoryService) {
        this.budgetCategoryService = budgetCategoryService;
        this.budgetService = budgetService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<BudgetCategoryDto>> getAllBudgetCategories() {
        List<BudgetCategoryDto> budgetCategoryDtos = budgetCategoryService.getAllBudgetCategories().stream()
                .map(budgetCategory -> new BudgetCategoryDto(budgetCategory.getBudget().getId(), budgetCategory.getCategory().getId(), budgetCategory.getBudgetLimit()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(budgetCategoryDtos);
    }

    @PostMapping
    public ResponseEntity<BudgetCategoryDto> createBudgetCategory(@RequestBody BudgetCategoryDto budgetCategoryDto) {
        Budget budget = budgetService.getBudgetById(budgetCategoryDto.getBudgetId());
        Category category = categoryService.getCategoryById(budgetCategoryDto.getCategoryId());

        BudgetCategory budgetCategory = new BudgetCategory(budget, category, budgetCategoryDto.getBudgetLimit());
        BudgetCategory savedBudgetCategory = budgetCategoryService.saveBudgetCategory(budgetCategory);

        BudgetCategoryDto responseDto = new BudgetCategoryDto(savedBudgetCategory.getBudget().getId(), savedBudgetCategory.getCategory().getId(), savedBudgetCategory.getBudgetLimit());
        return ResponseEntity.status(201).body(responseDto);
    }
}