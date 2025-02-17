package com.example.ecommerce_api.services;

import com.example.ecommerce_api.domain.user.User;
import com.example.ecommerce_api.dto.RegisterRequestDTO;
import com.example.ecommerce_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
@RequiredArgsConstructor
class UserServiceTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userServiceInject;

    @Mock
    private UserService userServiceMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should save user successfully")
    void saveUser() {
        RegisterRequestDTO request = new RegisterRequestDTO("nome teste", "teste@gmail.com", "123123");
        User newUser = this.userServiceInject.createUser(request);

//        Mockito.doThrow(new Exception()).when(this.userServiceInject).saveUser(newUser);
//        assertThrows(Exception.class, () -> {
//            this.userServiceInject.saveUser(newUser);
//            this.userServiceInject.saveUser(newUser);
//        });
//        verify(userServiceMock, times(1)).saveUser(newUser);
//        assertTrue(foundUser.isPresent());
    }
}