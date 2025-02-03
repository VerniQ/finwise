package me.verni.budget;

import me.verni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long>{
    <Optional> List<Budget> findByUser(User user);
    <Optional> List<Budget> findByUserId(Long userId);
}
