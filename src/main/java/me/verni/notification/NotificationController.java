package me.verni.notification;

import me.verni.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationByUser(@PathVariable Long userId) {
        return notificationService.findByUserId(userId);
    }

    @PostMapping
    public Notification saveNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@RequestBody Notification notification) {
        return notificationService.updateNotification(notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
    
}
