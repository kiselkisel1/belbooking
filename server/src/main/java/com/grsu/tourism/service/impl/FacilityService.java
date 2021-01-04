package com.grsu.tourism.service.impl;

import com.grsu.tourism.model.Facility;
import com.grsu.tourism.model.Housing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FacilityService {
    private final HousingService housingService;

    public Housing save(Facility facility, Integer serviceId) {
        Housing housing = housingService.getById(serviceId);
        housing.getFacilities().add(facility);
        return housingService.saveService(housing);
    }
}