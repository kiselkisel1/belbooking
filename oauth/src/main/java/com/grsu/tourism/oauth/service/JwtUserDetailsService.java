package com.grsu.tourism.oauth.service;

import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Transactional
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userRepo.findByEmail(username);
        if (userDto == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userDto.getEmail(), userDto.getPassword(), getAuthority(userDto));
    }

    /**
     * User has only field Role role, because admin has privileges as user and admin.
     */
    private Set<SimpleGrantedAuthority> getAuthority(UserDto user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRole().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}