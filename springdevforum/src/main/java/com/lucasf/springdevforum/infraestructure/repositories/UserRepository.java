package com.lucasf.springdevforum.infraestructure.repositories;

import com.lucasf.springdevforum.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
