package com.grsu.tourism.converter;

import com.grsu.tourism.dto.CommentDto;
import com.grsu.tourism.model.user.Comment;
import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentDtoConverter implements Converter<Comment, CommentDto> {
    private final UserService userService;

    @Override
    public CommentDto convert(Comment source) {
        UserDto userDto = userService.getById(source.getUserId());
        CommentDto commentDto = CommentDto.builder()
                .id(source.getId())
                .commentDate(source.getCommentDate())
                .description(source.getDescription())
                .rating(source.getRating())
                .serviceId(source.getServiceId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .build();
        return commentDto;
    }
}
