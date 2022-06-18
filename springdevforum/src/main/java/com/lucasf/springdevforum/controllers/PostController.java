package com.lucasf.springdevforum.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/posts")
public class PostController {

    @GetMapping
    public ResponseEntity<String> findAll(){
        return ResponseEntity.ok().body("Tudo ok");
    }
}
