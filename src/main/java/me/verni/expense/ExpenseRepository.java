package me.verni.expense;

import me.verni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    <Optional> List<Expense> findByUser(User user);
    <Optional> List<Expense> findByUserId(Long userId);
}
