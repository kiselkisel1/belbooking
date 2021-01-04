package com.grsu.tourism.dto;

import com.grsu.tourism.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto<S extends AbstractService> {
    private S service;

    private List<ContactDetails> contactDetails;
    private List<Location> locations;
    private List<Picture> pictures;
    private List<Stock> stocks;
    private List<OpeningHours> openingHours;
}
