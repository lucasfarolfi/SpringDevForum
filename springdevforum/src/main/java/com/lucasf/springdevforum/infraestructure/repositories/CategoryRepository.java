package com.lucasf.springdevforum.infraestructure.repositories;

import com.lucasf.springdevforum.domain.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
