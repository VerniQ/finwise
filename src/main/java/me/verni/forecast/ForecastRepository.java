package me.verni.forecast;

import me.verni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    <Optional> List<Forecast> findByUser(User user);
    <Optional> List<Forecast> findByUserId(Long userId);
}
