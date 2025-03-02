package me.verni.budget.general;

import me.verni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);
    List<Budget> findByUserId(Long userId);
    List<Budget> findByUserIdAndMonth(Long userId, int month);
    List<Budget> findByUserIdAndYear(Long userId, int year);
    List<Budget> findByUserIdAndMonthAndYear(Long userId, int month, int year);
}