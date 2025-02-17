package com.example.ecommerce_api.services;

import com.example.ecommerce_api.domain.user.User;
import com.example.ecommerce_api.dto.RegisterRequestDTO;
import com.example.ecommerce_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Optional<User> findUserById(String id) {
        return this.userRepository.findById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User createUser(RegisterRequestDTO request) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());
        newUser.setName(request.name());
        this.userRepository.save(newUser);
        return newUser;
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
