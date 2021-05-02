package com.grsu.tourism.service.impl;

import com.google.common.collect.Lists;
import com.grsu.tourism.converter.ServiceConverter;
import com.grsu.tourism.dto.ServiceDto;
import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.repository.AbstractServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AbstractServiceImpl {
    private final AbstractServiceRepository serviceRepository;
    private final ServiceConverter serviceConverter;

    public Map<Integer, ServiceDto> getAllMap() {
        List<AbstractService> services = Lists.newArrayList(serviceRepository.findAll());
        List<ServiceDto> serviceDto = serviceConverter.convert(services);

        Map<Integer, ServiceDto> serviceById = new HashMap<>();
        if (!CollectionUtils.isEmpty(services)) {
            serviceById = serviceDto.stream()
                    .collect(Collectors.toMap(serviceDto1 -> serviceDto1.getService().getId(), a -> a));
        }
        return serviceById;
    }

    public List<ServiceDto> getAll() {
        List<AbstractService> services = Lists.newArrayList(serviceRepository.findAll());
        return serviceConverter.convert(services);
    }

    public List<AbstractService> getByType(ServiceType type, Pageable pageable) {
        List<AbstractService> services = Lists.newArrayList(serviceRepository.findByTypeAndIsBooked(type.getName(), pageable, true));
        return services;
    }

    public List<AbstractService> getByTypeAndSubType(ServiceType type, String subType, Pageable pageable) {
        List<AbstractService> services = Lists.newArrayList(serviceRepository.findByTypeAndSubTypeAndIsBooked(type.getName(), subType, pageable, true));
        return services;
    }

    public Set<Integer> getServiceIds(List<AbstractService> serviceDtos) {
        return serviceDtos.stream()
                .map(AbstractService::getId)
                .collect(Collectors.toSet());
    }

}