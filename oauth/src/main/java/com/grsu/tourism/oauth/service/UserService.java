package com.grsu.tourism.oauth.service;

import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserDto save(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public UserDto getByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }

    public UserDto getCurrentUserInfo() {
        String email = getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));
        return userDto;
    }
}
