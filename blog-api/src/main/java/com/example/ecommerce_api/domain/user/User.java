package com.example.ecommerce_api.domain.user;

import com.example.ecommerce_api.domain.post.Post;
import com.example.ecommerce_api.dto.RegisterRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany()
    @JoinColumn(name = "idAuthor")
    private Set<Post> posts = new HashSet<>();
}
