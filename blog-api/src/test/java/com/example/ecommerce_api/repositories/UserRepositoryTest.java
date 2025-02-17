package com.example.ecommerce_api.repositories;

import com.example.ecommerce_api.domain.user.User;
import com.example.ecommerce_api.dto.RegisterRequestDTO;
import com.example.ecommerce_api.services.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@RequiredArgsConstructor
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User from DB successfully")
    void findByEmailCase1() {
        RegisterRequestDTO request = new RegisterRequestDTO("nome teste", "teste@gmail.com", "123123");
        User newUser = new User();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(request.password());
        this.entityManager.persist(newUser);

        Optional<User> result = this.userRepository.findByEmail(request.email());

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Should not get User from DB when don't exist")
    void findByEmailCase2() {
        RegisterRequestDTO request = new RegisterRequestDTO("nome teste", "teste@gmail.com", "123123");

        Optional<User> result = this.userRepository.findByEmail(request.email());
        assertTrue(result.isEmpty());
    }
}