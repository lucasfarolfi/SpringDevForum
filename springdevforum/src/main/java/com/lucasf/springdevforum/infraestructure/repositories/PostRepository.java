package com.lucasf.springdevforum.infraestructure.repositories;

import com.lucasf.springdevforum.domain.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {
    @Query("{ 'categories': { $elemMatch: { 'id' :  ?0 } } }")
    Page<Post> findByCategoryId(String id, Pageable pageable);

    @Query("{ 'author.id': ?0 } ")
    Page<Post> findByUserId(String userId, Pageable pageable);
}
