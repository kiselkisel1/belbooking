package com.grsu.tourism.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "housing")
@PrimaryKeyJoinColumn(name = "id")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Housing  extends AbstractService {
    private Integer stars;
    private Double center_distance;
    @Column(name = "category")
    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "housing_id")
    private List<Facility> facilities;
}
