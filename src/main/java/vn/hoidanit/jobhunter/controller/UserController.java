package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

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

    @GetMapping("/user")
    public List<User> getAllUser() {
        return this.userServices.fetchAllUser();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return this.userServices.fetchGetUserById(id);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return this.userServices.handleUpdateUser(user);
    }
}
