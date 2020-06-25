package emt.miniproekt.userrequest.domain.service;

import emt.miniproekt.userrequest.application.ComplaintRequestRepo;
import emt.miniproekt.userrequest.domain.event.ComplaintRequestEvent;
import emt.miniproekt.userrequest.domain.model.ComplaintRequest;
import emt.miniproekt.userrequest.domain.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UsersService {

    ApplicationEventPublisher applicationEventPublisher;
    ComplaintRequestRepo complaintRequestRepo;


    public UsersService(ApplicationEventPublisher applicationEventPublisher,
                        ComplaintRequestRepo complaintRequestRepo){
        this.applicationEventPublisher = applicationEventPublisher;
        this.complaintRequestRepo = complaintRequestRepo;
    }

    public void makeComplaintRequest(int employeeId, String description, User user){
        ComplaintRequest cr = new ComplaintRequest(employeeId, description, user);
        complaintRequestRepo.saveNewComplaintRequest(cr);

        ComplaintRequestEvent event = new ComplaintRequestEvent(cr.getEmployeeId(), Instant.now(), cr.getDescription());
        applicationEventPublisher.publishEvent(event);
    }
}
