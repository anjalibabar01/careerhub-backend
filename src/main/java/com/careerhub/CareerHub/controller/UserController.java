package com.careerhub.CareerHub.controller;

import com.careerhub.CareerHub.dto.LoginResponse;
import com.careerhub.CareerHub.entity.User;
import com.careerhub.CareerHub.jwt.JwtUtil;
import com.careerhub.CareerHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/test")
    public String test() {
        return "API Working";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        return userService.updateUser(id, user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {

        System.out.println("LOGIN API HIT");
        System.out.println(user.getEmail());

        User existingUser = userService.login(user.getEmail());

        if (existingUser != null &&
                passwordEncoder.matches(
                        user.getPassword(),
                        existingUser.getPassword())) {

            String token = jwtUtil.generateToken(existingUser.getEmail());

            return new LoginResponse(
                    token,
                    existingUser.getFullName(),
                    existingUser.getEmail(),
                    existingUser.getRole()
            );
        }

        throw new RuntimeException("Invalid email or password");
    }
}