package com.grsu.tourism.model.user;

import com.grsu.tourism.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer serviceId;
    private Integer userId;
    private String serviceType;

    private String description;
    private LocalDateTime commentDate;
    private Double rating;

}