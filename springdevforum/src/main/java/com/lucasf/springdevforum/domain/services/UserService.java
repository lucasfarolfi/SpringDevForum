package com.lucasf.springdevforum.domain.services;

import com.lucasf.springdevforum.domain.entities.User;
import com.lucasf.springdevforum.application.dtos.UserDto;
import com.lucasf.springdevforum.application.exceptions.ObjectNotFoundException;
import com.lucasf.springdevforum.infraestructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<UserDto> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserDto::new);
    }

    public UserDto findById(String id){
        Optional<User> userOpt = userRepository.findById(id);
        User user = userOpt.orElseThrow(() -> new ObjectNotFoundException("User not found."));
        return new UserDto(user);
    }

}
