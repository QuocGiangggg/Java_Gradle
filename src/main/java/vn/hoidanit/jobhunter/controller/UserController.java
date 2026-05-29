package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserServices;

@RestController
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User user) {

        User newUser = this.userServices.handleCreateUser(user);
        return newUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.userServices.handleDeleteUser(id);
        return "Delete user successfully";
    }
}
