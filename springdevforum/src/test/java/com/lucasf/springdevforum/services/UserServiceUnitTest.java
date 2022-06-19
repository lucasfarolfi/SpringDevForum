package com.lucasf.springdevforum.services;

import com.lucasf.springdevforum.domain.Category;
import com.lucasf.springdevforum.domain.User;
import com.lucasf.springdevforum.dtos.AuthorDto;
import com.lucasf.springdevforum.dtos.UserDto;
import com.lucasf.springdevforum.exceptions.ObjectNotFoundException;
import com.lucasf.springdevforum.repositories.CategoryRepository;
import com.lucasf.springdevforum.repositories.UserRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Given users Page, When get all users, Then userDto Page is returned")
    public void test1(){
        // Given
        Pageable pageable = PageRequest.of(1, 2);
        Page<User> users = createUserPage(pageable);
        Mockito.when(userRepository.findAll(pageable)).thenReturn(users);

        // When
        Page<UserDto> result = userService.findAll(pageable);

        // Then
        assertThat(result).isEqualTo(users.map(UserDto::new));
    }

    @Test
    @DisplayName("Given empty users Page, When get all users, Then empty list is returned")
    public void test2(){
        // Given
        Pageable pageable = PageRequest.of(1, 2);
        Page<User> users = createEmptyUserPage(pageable);
        Mockito.when(userRepository.findAll(pageable)).thenReturn(users);

        // When
        Page<User> result = userRepository.findAll(pageable);

        // Then
        assertThat(result).isEqualTo(users);
    }

    @Test
    @DisplayName("Given existent user id, When get user by id, Then userDto is returned")
    public void test3(){
        // Given
        String id = "507f1f77bcf86cd799439011";
        User user = new User("User", "Test", "email@email.com", "testpassword");
        user.setId(id);
        Optional<User> optionalUser = Optional.ofNullable(user);
        Mockito.when(userRepository.findById(id)).thenReturn(optionalUser);

        // When
        UserDto result = userService.findById(id);

        // Then
        assertThat(result).isEqualTo(new UserDto(user));
    }

    @Test
    @DisplayName("Given nonexistent user id, When get user by id, Then ObjectNotFound exception is Thrown")
    public void test4(){
        // Given
        String id = "507f1f77bcf86cd799439011";
        Optional<User> optionalUser = Optional.empty();
        Mockito.when(userRepository.findById(id)).thenReturn(optionalUser);

        // When
        Throwable thrown = catchThrowable(() -> userService.findById(id));

        // Then
        assertThat(thrown).isInstanceOf(ObjectNotFoundException.class).hasMessage("User not found.");
    }

    // Test data
    public Page<User> createUserPage(Pageable pageable){
        User user1 = new User("User", "Test", "email@email.com", "testpassword");
        User user2 = new User("User", "Test", "email@email.com", "testpassword");
        List<User> categories = new ArrayList<>(Arrays.asList(user1,user2));

        return new PageImpl<User>(categories, pageable, categories.size());
    }

    public Page<User> createEmptyUserPage(Pageable pageable){
        List<User> users = new ArrayList<>();

        return new PageImpl<User>(users, pageable, users.size());
    }
}