package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @GetMapping()
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping()
    public ResponseEntity<Void> createGroup() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable int groupId) {
        return ResponseEntity.ok(new GroupDto());
    }

    @PutMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable int groupId) {
        return ResponseEntity.ok(new GroupDto());
    }


}
