package com.example.ecommerce_api.repositories;

import com.example.ecommerce_api.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
