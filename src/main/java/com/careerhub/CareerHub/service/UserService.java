package com.careerhub.CareerHub.service;

import com.careerhub.CareerHub.entity.User;
import com.careerhub.CareerHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User saveUser(User user) {

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id).orElse(null);

        if(existingUser != null){
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null &&
                    !updatedUser.getPassword().isBlank()) {

                existingUser.setPassword(
                        passwordEncoder.encode(updatedUser.getPassword())
                );
            }
            existingUser.setRole(updatedUser.getRole());

            return userRepository.save(existingUser);
        }

        return null;
    }

    public User login(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}