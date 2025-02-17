package com.example.ecommerce_api.controllers;

import com.example.ecommerce_api.domain.user.User;
import com.example.ecommerce_api.dto.LoginRequestDTO;
import com.example.ecommerce_api.dto.RegisterRequestDTO;
import com.example.ecommerce_api.dto.ResponseDTO;
import com.example.ecommerce_api.infra.security.TokenService;
import com.example.ecommerce_api.repositories.UserRepository;
import com.example.ecommerce_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Optional<User> user = this.userService.findUserByEmail(body.email());

        if (user.isEmpty() || !passwordEncoder.matches(body.password(), user.get().getPassword()))
            return ResponseEntity.badRequest().body("E-mail e/ou senha incorretos!");

        String token = this.tokenService.generateToken(user.get());
        return ResponseEntity.ok(new ResponseDTO(user.get().getName(), token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.userService.findUserByEmail((body.email()));

        if(user.isEmpty()) {
            User newUser = this.userService.createUser(body);
            this.userService.saveUser(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().body("Já existe uma conta com este e-mail!");
    }
}