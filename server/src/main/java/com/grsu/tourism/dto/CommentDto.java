package com.grsu.tourism.dto;

import com.grsu.tourism.oauth.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Integer id;
    private Integer serviceId;
    private String name;
    private String surname;
    private String email;

    private String description;
    private LocalDateTime commentDate;
    private Double rating;
}
