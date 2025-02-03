package me.verni.forecast;

import me.verni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    <Optional> List<Forecast> findByUser(User user);
    <Optional> List<Forecast> findByUserId(Long userId);
}
