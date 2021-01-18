package com.grsu.tourism.validator;

import com.grsu.tourism.repository.AbstractServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateUtil {
    private final AbstractServiceRepository serviceRepository;

    public void isServiceExists(Integer serviceId) {
        serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service with such id does not exist " + serviceId));
    }
}
