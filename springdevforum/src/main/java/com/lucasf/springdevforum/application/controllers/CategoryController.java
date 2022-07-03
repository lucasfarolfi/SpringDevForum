package com.lucasf.springdevforum.application.controllers;

import com.lucasf.springdevforum.domain.entities.Category;
import com.lucasf.springdevforum.domain.entities.Post;
import com.lucasf.springdevforum.domain.services.CategoryService;
import com.lucasf.springdevforum.domain.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Category> categories = categoryService.findAll(pageable);
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable String id){
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<Page<Post>> posts(
            @PathVariable String id, @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Post> posts = postService.findByCategoryId(id, pageable);
        return ResponseEntity.ok().body(posts);
    }
}
