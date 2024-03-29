package com.grsu.tourism.oauth.controller;

import com.grsu.tourism.oauth.conf.JwtTokenService;
import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenUtil;
    private final UserService userService;

    @PostMapping(value = "/auth/token")
    public String createAuthenticationToken(@RequestBody UserDto authenticationRequest) throws Exception {

        final Authentication auth = authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtTokenUtil.generateToken(auth);
    }

    @PostMapping(value = "/register")
    public UserDto saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value = "/getCurrentUser/info")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public UserDto getUser() {
        return userService.getCurrentUserInfo();
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}