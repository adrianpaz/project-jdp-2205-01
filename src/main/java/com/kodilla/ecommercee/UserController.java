package com.kodilla.ecommercee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "Users list";
    }

    @GetMapping(value = "{userId}")
    public String getUser(@PathVariable Long userId) {
        return "Get User with specified userId";
    }

    @DeleteMapping(value = "{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return "Remove User with specified userId";
    }

    @PutMapping
    public String updateUser() {
        return "Update User with @RequestedBody UserDto";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createUser() {
        return "Create new User with @RequestedBody UserDto";
    }
}
