package com.achraf.minibankbackend.controllers;

import com.achraf.minibankbackend.models.User;
import com.achraf.minibankbackend.services.implementations.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users/")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getuser/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.status(OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/authenticate/")
    public ResponseEntity<?> authenticate(@RequestBody String username, @RequestBody String password) {
        try {
            Boolean loginStatus = userService.loginUser(username, password);
            return loginStatus ? ResponseEntity.status(OK).body("Login effective") : ResponseEntity.status(NOT_FOUND).body("Username/Password wrong");
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PatchMapping("/updateuser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        try {
            User updatedUser = userService.updateUser(id, updateUser);
            return ResponseEntity.status(OK).body(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(OK).body("User Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody User createUser) {
        try {
            User createdUser = userService.registerUser(createUser);
            return ResponseEntity.status(CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
