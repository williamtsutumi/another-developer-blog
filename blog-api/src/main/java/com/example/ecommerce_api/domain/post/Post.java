package com.example.ecommerce_api.domain.post;

import com.example.ecommerce_api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String subtitle;
    private String text;

    @ManyToOne
    @JoinColumn(name = "idAuthor")
    private User author;
}
