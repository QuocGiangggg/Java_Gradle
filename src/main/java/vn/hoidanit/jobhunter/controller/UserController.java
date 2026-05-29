package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserServices;

@RestController
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/user/create")
    public String createNewUser() {

        User user = new User();
        user.setName("DQG");
        user.setEmail("dqg@example.com");
        user.setPassword("123456");

        this.userServices.handleCreateUser(user);
        return "User created successfully!";
    }
}
