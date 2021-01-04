package com.grsu.tourism.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;


@Entity(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;
    private Role role;
//    private String phoneNumber;
//    private String mobilePhone;
//    private String email;
//    private String address;

}