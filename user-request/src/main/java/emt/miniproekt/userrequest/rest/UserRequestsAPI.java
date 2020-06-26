package emt.miniproekt.userrequest.rest;

import emt.miniproekt.userrequest.domain.model.ComplaintRequest;
import emt.miniproekt.userrequest.domain.model.User;
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
                                                @RequestParam("userId") int userId){
        return usersService.makeComplaintRequest(employeeId, description, userId);
    }

    @PostMapping("/makeNewUser")
    public User addUser(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        return usersService.makeNewUser(username, password);
    }


}
