package com.lucasf.springdevforum.repositories;

import com.lucasf.springdevforum.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
