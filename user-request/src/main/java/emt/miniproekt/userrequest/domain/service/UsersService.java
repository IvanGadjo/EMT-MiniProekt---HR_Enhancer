package emt.miniproekt.userrequest.domain.service;

import emt.miniproekt.userrequest.application.*;
import emt.miniproekt.userrequest.domain.event.ComplaintRequestEvent;
import emt.miniproekt.userrequest.domain.event.PositionChangeRequestEvent;
import emt.miniproekt.userrequest.domain.event.RaiseRequestEvent;
import emt.miniproekt.userrequest.domain.event.RestDaysRequestEvent;
import emt.miniproekt.userrequest.domain.model.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


// Biznis pravilata (1 request per employee, 15% max zgolemuvanje plata) se implementirani vo
// employee-sector modulot, bidejki na toj modul se primenuvaat promenite koi proizleguvaat od requests-ot
// od ovoj modul


@Service
@Transactional
public class UsersService {

    ApplicationEventPublisher applicationEventPublisher;
    ComplaintRequestRepo complaintRequestRepo;
    UserRepo userRepo;
    PositionChangeRequestRepo positionChangeRequestRepo;
    RaiseRequestRepo raiseRequestRepo;
    RestDaysRequestRepo restDaysRequestRepo;


    public UsersService(ApplicationEventPublisher applicationEventPublisher,
                        ComplaintRequestRepo complaintRequestRepo,
                        UserRepo userRepo, PositionChangeRequestRepo positionChangeRequestRepo,
                        RaiseRequestRepo raiseRequestRepo, RestDaysRequestRepo restDaysRequestRepo){
        this.applicationEventPublisher = applicationEventPublisher;
        this.complaintRequestRepo = complaintRequestRepo;
        this.userRepo = userRepo;
        this.positionChangeRequestRepo = positionChangeRequestRepo;
        this.raiseRequestRepo = raiseRequestRepo;
        this.restDaysRequestRepo = restDaysRequestRepo;
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
        ComplaintRequestEvent event = new ComplaintRequestEvent(cr.getId(), cr.getEmployeeId(), Instant.now(), cr.getDescription());
        applicationEventPublisher.publishEvent(event);

        return cr;
    }

    public PositionChangeRequest makePositionChangeRequest(int employeeId, String description, String newPosition, int userId) {
        User theUser = userRepo.findById(userId);

        PositionChangeRequest pcr = new PositionChangeRequest(employeeId, description, newPosition, theUser);
        positionChangeRequestRepo.saveNewPositionChangeRequest(pcr);

        theUser.addPositionChangeRequest(pcr);
        userRepo.saveNewUser(theUser);

        PositionChangeRequestEvent event = new PositionChangeRequestEvent(pcr.getId() ,pcr.getEmployeeId(), Instant.now(), pcr.getDescription(), pcr.getNewPosition());
        applicationEventPublisher.publishEvent(event);

        return pcr;
    }

    public RaiseRequest makeRaiseRequest(int employeeId, String description, double newSalary, int userId) {
        User theUser = userRepo.findById(userId);

        RaiseRequest rr = new RaiseRequest(employeeId, description, newSalary, theUser);
        raiseRequestRepo.saveNewRaiseRequest(rr);

        theUser.addRaiseRequest(rr);
        userRepo.saveNewUser(theUser);

        RaiseRequestEvent event = new RaiseRequestEvent(rr.getId(), rr.getEmployeeId(), Instant.now(), rr.getDescription(), rr.getNewSalary());
        applicationEventPublisher.publishEvent(event);

        return rr;
    }

    public RestDaysRequest makeRestDaysRequest(int employeeId, String description, int numDays, int userId) {
        User theUser = userRepo.findById(userId);

        RestDaysRequest rdr = new RestDaysRequest(employeeId, description, numDays, theUser);
        restDaysRequestRepo.saveNewRestDaysRequest(rdr);

        theUser.addRestDaysRequest(rdr);
        userRepo.saveNewUser(theUser);

        RestDaysRequestEvent event = new RestDaysRequestEvent(rdr.getId(), rdr.getEmployeeId(), Instant.now(), rdr.getDescription(), rdr.getNumDays());
        applicationEventPublisher.publishEvent(event);

        return rdr;
    }

    public User makeNewUser(String username, String password){
        return userRepo.saveNewUser(new User(username,password));
    }
}
