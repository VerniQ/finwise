package me.verni.notification;

import me.verni.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    <Optional> List<Notification> findByUser(User user);
    <Optional> List<Notification> findByUserId(Long userId);
}
