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

    public Group mapToGroup(final GroupDto groupDto) throws ProductNotFoundException {
        return new Group(
                groupDto.getId(),
                groupDto.getName(),
                productMapper.mapToProductList(groupDto.getProducts())
        );
    }

    public GroupDto mapToGroupDto(final Group group) throws ProductNotFoundException {
        return new GroupDto(
                group.getId(),
                group.getName(),
                productMapper.mapToProductDtoList(group.getProducts())
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        List<GroupDto> collect = new ArrayList<>();
        for (Group group : groupList) {
            GroupDto groupDto = null;
            try {
                groupDto = mapToGroupDto(group);
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(groupDto);
        }
        return collect;
    }

    public List<Group> maToGroupList(List<GroupDto> groupDtoList) {
        List<Group> collect = new ArrayList<>();
        for (GroupDto groupDto : groupDtoList) {
            Group group = null;
            try {
                group = mapToGroup(groupDto);
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(group);
        }
        return collect;
    }
}
