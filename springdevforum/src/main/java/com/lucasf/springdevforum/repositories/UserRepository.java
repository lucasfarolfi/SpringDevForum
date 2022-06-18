package com.lucasf.springdevforum.repositories;

import com.lucasf.springdevforum.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
