package com.grsu.tourism.oauth.controller;

import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
//    @Operation(summary = "Sign up")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Item found", content =
//            @Content(schema = @Schema(implementation = UserDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
//            @ApiResponse(responseCode = "404", description = "Type is not found", content =
//            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
//            @ApiResponse(responseCode = "500", description = "System error", content =
//            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public UserDto signUp(@RequestBody UserDto user) {
       return userService.save(user);
    }
}