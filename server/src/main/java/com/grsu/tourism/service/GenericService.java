package com.grsu.tourism.service;


import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.AbstractService;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<S extends AbstractService> {
    List<S> getAllByType(Pageable pageable);

    List<S> getAllByTypeAndSubType(String subType, Pageable pageable);

    S saveService(S service);

    S getById(Integer serviceId);

    void deleteService(Integer id);

    void setIsBooked(Integer serviceId, Boolean value);

    ServiceType getType();
}
