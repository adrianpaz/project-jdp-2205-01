package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;

import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupMapper groupMapper;
    private final GroupDBService groupDBService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<Group> groupList = groupDBService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groupList));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupDBService.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long groupId) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupDBService.getGroupById(groupId)));
    }

    @PutMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group saveGroup = groupDBService.saveGroup(group);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(saveGroup));
    }
}