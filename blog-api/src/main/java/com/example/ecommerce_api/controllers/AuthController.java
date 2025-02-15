package com.example.ecommerce_api.controllers;

import com.example.ecommerce_api.domain.user.User;
import com.example.ecommerce_api.dto.LoginRequestDTO;
import com.example.ecommerce_api.dto.RegisterRequestDTO;
import com.example.ecommerce_api.dto.ResponseDTO;
import com.example.ecommerce_api.infra.security.TokenService;
import com.example.ecommerce_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElse(null);

        if (user == null || !passwordEncoder.matches(body.password(), user.getPassword())) {
            return ResponseEntity.badRequest().body("E-mail e/ou senha incorretos!");
        }

        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().body("Já existe uma conta com este e-mail!");
    }
}