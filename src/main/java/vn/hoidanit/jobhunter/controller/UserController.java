package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserServices;

@RestController
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        User newUser = this.userServices.handleCreateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        this.userServices.handleDeleteUser(id);
        // return ResponseEntity.status(HttpStatus.OK).body("User deleted
        // successfully");
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userServices.fetchAllUser());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = this.userServices.fetchGetUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = this.userServices.handleUpdateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
