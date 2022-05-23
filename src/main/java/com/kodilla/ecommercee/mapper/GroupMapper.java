package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupMapper {

    private final ProductMapper productMapper;

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(
                groupDto.getId(),
                groupDto.getName(),
                productMapper.mapToProductList(groupDto.getProducts())
        );
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getName(),
                productMapper.mapToProductDtoList(group.getProducts())
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
