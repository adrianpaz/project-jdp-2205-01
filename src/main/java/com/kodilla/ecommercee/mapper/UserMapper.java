package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final OrderMapper orderMapper;


    public User mapToUser(final UserDto userDto) throws UserNotFoundException {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getStatus(),
                userDto.getUserKey(),
                orderMapper.mapToOrderList(userDto.getOrders())
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey(),
                orderMapper.mapToOrderDtoList(user.getOrders())
        );
    }
}
