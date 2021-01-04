package com.grsu.tourism.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "service")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    //TODO change on enum
    private String type;
    @Column(name = "subtype")
    private String subType;
    private Double price;
    @Column(name = "is_booked")
    private Boolean isBooked;
    @Column(name = "is_active")
    private Boolean isActive;
}
