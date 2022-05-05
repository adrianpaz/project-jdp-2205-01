package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(new UserDto());
    }

    @PutMapping()
    public ResponseEntity<Long> generateKey() {
        return ResponseEntity.ok(59404L);
    }
}
