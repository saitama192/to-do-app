package com.example.to_do_app.service;

import com.example.to_do_app.model.Role;
import com.example.to_do_app.model.User;
import com.example.to_do_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user, boolean isAdmin) {
        if (isAdmin) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        } else {
            user.setRoles(Collections.singleton(Role.USER));
        }
        return userRepository.save(user);
    }
}
