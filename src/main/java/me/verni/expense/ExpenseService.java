package me.verni.expense;

import me.verni.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpenses(){
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> findByUser(User user){
        return expenseRepository.findByUser(user);
    }

    public List<Expense> findByUserId(Long userId){
        return expenseRepository.findByUserId(userId);
    }
}
