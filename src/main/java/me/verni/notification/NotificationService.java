package me.verni.notification;

import me.verni.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification saveNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public List<Notification> findByUserId(Long userId){
        return notificationRepository.findByUserId(userId);
    }

    public List<Notification> findByUser(User user){
        return notificationRepository.findByUser(user);
    }
    public Notification updateNotification(Notification notification){
        return notificationRepository.save(notification);
    }
    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }

}
