package com.lucasf.springdevforum.application.controllers;

import com.lucasf.springdevforum.domain.entities.Post;
import com.lucasf.springdevforum.application.dtos.UserDto;
import com.lucasf.springdevforum.domain.services.PostService;
import com.lucasf.springdevforum.domain.services.UserService;
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
@RequestMapping(value = "/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<UserDto> users = userService.findAll(pageable);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id){
        UserDto user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<Page<Post>> posts(
            @PathVariable String id, @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Post> posts = postService.findByUserId(id, pageable);
        return ResponseEntity.ok().body(posts);
    }
}
