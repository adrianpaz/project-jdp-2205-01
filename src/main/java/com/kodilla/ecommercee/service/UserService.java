package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Token;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(final User user) throws UserNotFoundException{;
        userRepository.save(user);
    }

    public User getUser(final Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void blockUser(final Long userId) throws UserNotFoundException {
        User user = getUser(userId);
        user.setStatus("0");
        userRepository.save(user);
    }

    public Long generateToken(final Long userId) throws UserNotFoundException {
        User user = getUser(userId);
        Random randomGenerator = new Random();
        int generatedKey = randomGenerator.nextInt(99999 - 10000) + 10000;
        LocalTime keyCreationTime = LocalTime.now();
        LocalTime keyExpirationTime = keyCreationTime.plusHours(1);
        new Token((long)generatedKey, keyCreationTime, keyExpirationTime);
        user.setUserKey((long)generatedKey);
        userRepository.save(user);
        return user.getUserKey();
    }
}
