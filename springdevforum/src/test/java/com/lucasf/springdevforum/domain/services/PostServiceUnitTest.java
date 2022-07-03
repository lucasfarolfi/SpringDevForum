package com.lucasf.springdevforum.domain.services;

import com.lucasf.springdevforum.domain.entities.Category;
import com.lucasf.springdevforum.domain.entities.Post;
import com.lucasf.springdevforum.domain.enums.PostStatus;
import com.lucasf.springdevforum.domain.entities.User;
import com.lucasf.springdevforum.application.dtos.AuthorDto;
import com.lucasf.springdevforum.application.exceptions.ObjectNotFoundException;
import com.lucasf.springdevforum.infraestructure.repositories.PostRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceUnitTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("Given posts page, When find all posts, Then posts page is returned")
    public void test1(){
        // Given
        Pageable pageable = PageRequest.of(1, 2);
        Page<Post> postsPage = createPostPage(pageable);
        Mockito.when(postRepository.findAll(pageable)).thenReturn(postsPage);

        // When
        Page<Post> result = postService.findAll(pageable);

        // Then
        assertThat(result).isEqualTo(postsPage);
    }

    @Test
    @DisplayName("Given empty posts page, When find all posts, Then empty page is returned")
    public void test2(){
        // Given
        Pageable pageable = PageRequest.of(1, 2);
        List<Post> postsEmptyList = new ArrayList<>();
        Page<Post> postsPage = new PageImpl<>(postsEmptyList, pageable, postsEmptyList.size());
        Mockito.when(postRepository.findAll(pageable)).thenReturn(postsPage);

        // When
        Page<Post> result = postService.findAll(pageable);

        // Then
        assertThat(result).isEqualTo(postsPage);
    }

    @Test
    @DisplayName("Given existent post id, When find post by id, Then post is returned")
    public void test3(){
        // Given
        String id = "507f1f77bcf86cd799439011";
        Post postTest = createPost(id);

        Optional<Post> optionalPost = Optional.ofNullable(postTest);
        Mockito.when(postRepository.findById(id)).thenReturn(optionalPost);

        // When
        Post result = postService.findById(id);

        // Then
        assertThat(result).isEqualTo(postTest);
    }

    @Test
    @DisplayName("Given nonexistent post id, When find post by id, Then ObjectNotFoundException is Thrown")
    public void test4(){
        // Given
        String id = "507f1f77bcf86cd799439011";

        Optional<Post> optionalPost = Optional.empty();
        Mockito.when(postRepository.findById(id)).thenReturn(optionalPost);

        // When
        Throwable throwable = catchThrowable(() -> postService.findById(id));

        // Then
        assertThat(throwable).isInstanceOf(ObjectNotFoundException.class).hasMessage("Post not found.");
    }

    @Test
    @DisplayName("Given existent category id and posts page, When find posts by category id, Then posts page is returned")
    public void test5(){
        // Given
        String categoryId = "507f1f77bcf86cd799439011";
        Pageable pageable = PageRequest.of(1, 2);
        Page<Post> postsPage = createPostsWithSameId(categoryId, pageable);
        Mockito.when(postRepository.findByCategoryId(categoryId, pageable)).thenReturn(postsPage);

        // When
        Page<Post> result = postService.findByCategoryId(categoryId, pageable);

        // Then
        assertThat(result).isEqualTo(postsPage);
    }

    @Test
    @DisplayName("Given nonexistent category id and posts page, When find posts by category id, Then empty page is returned")
    public void test6(){
        // Given
        String id = "507f1f77bcf86cd799439011";
        Pageable pageable = PageRequest.of(1, 2);
        List<Post> postsEmptyList = new ArrayList<>();
        Page<Post> postsPage = new PageImpl<>(postsEmptyList, pageable, postsEmptyList.size());
        Mockito.when(postRepository.findByCategoryId(id, pageable)).thenReturn(postsPage);

        // When
        Page<Post> result = postService.findByCategoryId(id, pageable);

        // Then
        assertThat(result).isEqualTo(postsPage);
    }

    @Test
    @DisplayName("Given existent author id and posts page, When find posts by user id, Then posts page is returned")
    public void test7(){
        // Given
        String authorId = "507f1f77bcf86cd799439011";
        Pageable pageable = PageRequest.of(1, 2);
        Page<Post> postsPage = createPostsWithSameId(authorId, pageable);
        Mockito.when(postRepository.findByUserId(authorId, pageable)).thenReturn(postsPage);

        // When
        Page<Post> result = postService.findByUserId(authorId, pageable);

        // Then
        assertThat(result).isEqualTo(postsPage);
    }

    @Test
    @DisplayName("Given nonexistent author id and posts page, When find posts by user id, Then empty page is returned")
    public void test8(){
        // Given
        String authorId = "507f1f77bcf86cd799439011";
        Pageable pageable = PageRequest.of(1, 2);
        List<Post> postsEmptyList = new ArrayList<>();
        Page<Post> postsPage = new PageImpl<>(postsEmptyList, pageable, postsEmptyList.size());
        Mockito.when(postRepository.findByUserId(authorId, pageable)).thenReturn(postsPage);

        // When
        Page<Post> result = postService.findByUserId(authorId, pageable);

        // Then
        assertThat(result).isEqualTo(postsPage);
    }

    // Test data population
    public AuthorDto createAuthorDto(String id){
        User user = new User("User", "Test", "email@email.com", "testpassword");
        user.setId(id);
        return new AuthorDto(user);
    }

    public Category createCategory(String id){
        Category category = new Category("Category"+id);
        category.setId(id);
        return category;
    }

    public Post createPost(String id){
        AuthorDto authorDto = createAuthorDto(id);
        Category category = createCategory(id);

        Post post = new Post("Test1", "Description1", PostStatus.SOLVED, authorDto);
        post.setId(id);
        post.getCategories().addAll(Arrays.asList(category));

        return post;
    }

    public Page<Post> createPostPage(Pageable pageable){
        String id1 = "507f1f77bcf86cd799439011";
        String id2 = "507f1f77bcf86cd799439012";
        Post p1 = createPost(id1);
        Post p2 = createPost(id2);

        List<Post> posts = new ArrayList<>(Arrays.asList(p1,p2));

        return new PageImpl<>(posts, pageable, posts.size());
    }

    public Page<Post> createPostsWithSameId(String id, Pageable pageable){
        Post p1 = createPost(id);
        Post p2 = createPost(id);

        List<Post> posts = new ArrayList<>(Arrays.asList(p1,p2));

        return new PageImpl<>(posts, pageable, posts.size());
    }
}