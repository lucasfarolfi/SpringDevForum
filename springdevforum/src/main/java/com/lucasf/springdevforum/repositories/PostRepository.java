package com.lucasf.springdevforum.repositories;

import com.lucasf.springdevforum.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
