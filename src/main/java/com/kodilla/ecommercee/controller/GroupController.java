package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) {
        return ResponseEntity.ok(new GroupDto());
    }

    @PutMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long groupId,@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok(groupDto);
    }


}
