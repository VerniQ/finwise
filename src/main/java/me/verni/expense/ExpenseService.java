package me.verni.expense;

import me.verni.expense.dto.ExpenseResponseDto;
import me.verni.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseResponseDto> getExpenses(){
        return expenseRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> findByUserId(Long userId){
        return expenseRepository.findByUserId(userId);
    }

    public Expense updateExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id){
        if (!expenseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found");
        }
        expenseRepository.deleteById(id);
    }

    public Expense getExpenseById(Long id){
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found"));
    }

    private ExpenseResponseDto mapToResponseDto(Expense expense) {
        return new ExpenseResponseDto(
                expense.getExpenseId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory().getName(),
                expense.getExpenseDate()
        );
    }
}
