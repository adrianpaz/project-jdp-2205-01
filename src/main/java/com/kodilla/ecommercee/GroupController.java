package com.kodilla.ecommercee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @GetMapping()
    public String getGroup() {
        return "Group";
    }

    @DeleteMapping()
    public String deleteTask() {
        return "Group has been deleted";
    }

    @PutMapping
    public String updateTask() {
        return "Group has been update";
    }

    @PostMapping()
    public String createTask() {
        return "Group has been added";
    }
}
