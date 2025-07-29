package com.cigna.benefit.controller;

import com.cigna.benefit.model.BenefitEvents;
import com.cigna.benefit.repository.BenefitEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private BenefitEventsRepository repository;

    @GetMapping
    public List<BenefitEvents> getAllNotifications() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<BenefitEvents> getNotificationById(@PathVariable String id) {
        return repository.findById(id);
    }



    @GetMapping("/unread/{id}")
    public List<BenefitEvents> getUnreadNotifications(@PathVariable String id) {
        return repository.findByReadFalse(id);
    }


    @PutMapping("/{id}/read")
    public BenefitEvents markAsRead(@PathVariable String id) {
        Optional<BenefitEvents> optional = repository.findById(id);
        if (optional.isPresent()) {
            BenefitEvents notification = optional.get();
            notification.setRead(true);
            return repository.save(notification);
        }
        throw new RuntimeException("Notification not found with id " + id);
    }



    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable String id) {
        repository.deleteById(id);
    }
}

