package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public User saveUser(final User user) {
        user.setStatus("1");
        return userRepository.save(user);
    }

    public User blockUser(final User user) {
        user.setStatus("0");
        return userRepository.save(user);
    }

}
