package com.lucasf.springdevforum.services;

import com.lucasf.springdevforum.domain.Post;
import com.lucasf.springdevforum.exceptions.ObjectNotFoundException;
import com.lucasf.springdevforum.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Page<Post> findAll(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    public Post findById(String id){
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Post not found."));
    }

    public Page<Post> findByCategoryId(String categoryId, Pageable pageable) {
        return postRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<Post> findByUserId(String userId, Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }
}
