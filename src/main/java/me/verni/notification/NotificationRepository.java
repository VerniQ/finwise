package me.verni.notification;

import me.verni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    <Optional> List<Notification> findByUser(User user);
    <Optional> List<Notification> findByUserId(Long userId);
}
