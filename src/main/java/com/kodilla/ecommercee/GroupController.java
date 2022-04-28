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
    public String deleteGroup() {
        return "Group has been deleted";
    }

    @PutMapping
    public String updateGroup() {
        return "Group has been update";
    }

    @PostMapping()
    public String createGroup() {
        return "Group has been added";
    }
}
