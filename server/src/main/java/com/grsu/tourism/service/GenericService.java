package com.grsu.tourism.service;


import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.AbstractService;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<S extends AbstractService> {
    List<S> getAllByType(Pageable pageable);

    List<S> getAllByTypeAndSubType(String subType, Pageable pageable);

    S saveService(S service);

    S getById(Integer serviceId) throws NotFoundException;

    void deleteService(Integer id);

    ServiceType getType();
}
