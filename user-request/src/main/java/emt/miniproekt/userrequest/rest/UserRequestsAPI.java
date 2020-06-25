package emt.miniproekt.userrequest.rest;

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
    public void makeNewComplaintReq(@RequestParam("employeeId") int employeeId,
                                    @RequestParam("description") String description,
                                    @RequestParam("userId") int userId){
        // find user
        // userService.makeComplaintRequest()
    }
}
