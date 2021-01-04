package com.grsu.tourism.service.impl;

import com.grsu.tourism.enums.AttractionsEnum;
import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.repository.AbstractServiceRepository;
import com.grsu.tourism.service.GenericService;
import com.grsu.tourism.validator.ValidateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.grsu.tourism.enums.ServiceType.ATTRACTIONS;

@Service
@Transactional
@AllArgsConstructor
public class AttractionService implements GenericService<AbstractService> {

    private final AbstractServiceRepository abstractServiceRepository;
    private final ValidateService<AbstractService> validateService;

    @Override
    public List<AbstractService> getAllByType(Pageable pageable) {
        return abstractServiceRepository.findByType(ATTRACTIONS.getName(), pageable);
    }

    @Override
    public List<AbstractService> getAllByTypeAndSubType(String subType, Pageable pageable) {
        AttractionsEnum.getByNameIgnoreCaseOrElseThrow(subType);

        return abstractServiceRepository.findByTypeAndSubType(ATTRACTIONS.getName(), subType, pageable);
    }

    @Override
    public AbstractService saveService(AbstractService service) {
        validateService.validateTypeAndSubtype(service);
        return abstractServiceRepository.save(service);
    }

    @Override
    public AbstractService getById(Integer serviceId) {
        return abstractServiceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Attraction was not found for serviceId " + serviceId));
    }

    @Override
    public ServiceType getType() {
        return ATTRACTIONS;
    }

}