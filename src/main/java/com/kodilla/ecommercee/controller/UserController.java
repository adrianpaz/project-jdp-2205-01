package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Token;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) throws UserNotFoundException{
        userService.saveUser(userMapper.mapToUser(userDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/block_user/{userId}")
    public ResponseEntity<Void> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.blockUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/generate_key/{userId}")
    public ResponseEntity<Long> generateKey(@PathVariable Long userId) throws UserNotFoundException {
        Long userKey = userService.generateToken(userId);
        return ResponseEntity.ok(userKey);
    }
}