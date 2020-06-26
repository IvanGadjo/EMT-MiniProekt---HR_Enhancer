package emt.miniproekt.userrequest.domain.service;

import emt.miniproekt.userrequest.application.ComplaintRequestRepo;
import emt.miniproekt.userrequest.application.UserRepo;
import emt.miniproekt.userrequest.domain.event.ComplaintRequestEvent;
import emt.miniproekt.userrequest.domain.model.ComplaintRequest;
import emt.miniproekt.userrequest.domain.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class UsersService {

    ApplicationEventPublisher applicationEventPublisher;
    ComplaintRequestRepo complaintRequestRepo;
    UserRepo userRepo;


    public UsersService(ApplicationEventPublisher applicationEventPublisher,
                        ComplaintRequestRepo complaintRequestRepo,
                        UserRepo userRepo){
        this.applicationEventPublisher = applicationEventPublisher;
        this.complaintRequestRepo = complaintRequestRepo;
        this.userRepo = userRepo;
    }

    public ComplaintRequest makeComplaintRequest(int employeeId, String description, int userId){
        User theUser = userRepo.findById(userId);

        // update request
        ComplaintRequest cr = new ComplaintRequest(employeeId, description, theUser);
        complaintRequestRepo.saveNewComplaintRequest(cr);

        // update user
        theUser.addComplaintRequest(cr);
        userRepo.saveNewUser(theUser);

        // publish event
        ComplaintRequestEvent event = new ComplaintRequestEvent(cr.getEmployeeId(), Instant.now(), cr.getDescription());
        applicationEventPublisher.publishEvent(event);

        return cr;
    }

    public User makeNewUser(String username, String password){
        return userRepo.saveNewUser(new User(username,password));
    }
}
