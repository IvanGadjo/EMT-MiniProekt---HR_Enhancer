package emt.miniproekt.userrequest.rest;

import emt.miniproekt.userrequest.domain.model.*;
import emt.miniproekt.userrequest.domain.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/userRequests")
public class UserRequestsAPI {

    UsersService usersService;

    public UserRequestsAPI(UsersService usersService){
        this.usersService = usersService;
    }

    @PostMapping("/complaintRequest")
    public ComplaintRequest makeNewComplaintReq(@RequestParam("employeeId") int employeeId,
                                                @RequestParam("description") String description,
                                                @RequestParam("userId") int userId) {
        return usersService.makeComplaintRequest(employeeId, description, userId);
    }

    @PostMapping("/positionChangeRequest")
    public PositionChangeRequest makeNewPositionChangeReq(@RequestParam("employeeId") int employeeId,
                                                          @RequestParam("description") String description,
                                                          @RequestParam("newPosition") String newPosition,
                                                          @RequestParam("userId") int userId) {
        return usersService.makePositionChangeRequest(employeeId, description, newPosition, userId);
    }

    @PostMapping("/raiseRequest")
    public RaiseRequest makeNewRaiseReq(@RequestParam("employeeId") int employeeId,
                                        @RequestParam("description") String description,
                                        @RequestParam("newSalary") double newSalary,
                                        @RequestParam("userId") int userId) {
        return usersService.makeRaiseRequest(employeeId, description, newSalary, userId);
    }

    @PostMapping("/restDaysRequest")
    public RestDaysRequest makeNewRestDaysReq(@RequestParam("employeeId") int employeeId,
                                              @RequestParam("description") String description,
                                              @RequestParam("numDays") int numDays,
                                              @RequestParam("userId") int userId) {
        return usersService.makeRestDaysRequest(employeeId, description, numDays, userId);
    }

    @PostMapping("/makeNewUser")
    public User addUser(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        return usersService.makeNewUser(username, password);
    }


}
