package com.grsu.tourism.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "opening_hours")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpeningHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer serviceId;
    //enum
    private String dayOfWeek;
    private Timestamp openTime;
    private Timestamp closeTime;
}
