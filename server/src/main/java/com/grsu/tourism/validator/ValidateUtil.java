package com.grsu.tourism.validator;

import com.grsu.tourism.repository.AbstractServiceRepository;
import com.grsu.tourism.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateUtil {
    private final AbstractServiceRepository serviceRepository;
    private final RegionRepository regionRepository;

    public void isServiceExists(Integer serviceId) {
        serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service with such id does not exist " + serviceId));
    }

    public void isRegionExists(Integer region) {
        regionRepository.findById(region)
                .orElseThrow(() -> new IllegalArgumentException("Region with such id does not exist " + region));
    }
}
