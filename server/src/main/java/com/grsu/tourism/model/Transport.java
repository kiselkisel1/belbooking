package com.grsu.tourism.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "transport")
@PrimaryKeyJoinColumn(name = "id")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Transport extends AbstractService {

    private String leaseType;
    private String category;
}
