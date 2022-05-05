package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<UserDto> blockUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User blockedUser = userDbService.blockUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(blockedUser));
    }

    //To be implemented
    @PutMapping
    public Long generateKey() {
        return 59404L;
    }
}
