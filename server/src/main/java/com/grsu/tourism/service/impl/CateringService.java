package com.grsu.tourism.service.impl;

import com.grsu.tourism.converter.CateringConverter;
import com.grsu.tourism.enums.CateringEnum;
import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.model.Catering;
import com.grsu.tourism.service.GenericService;
import com.grsu.tourism.validator.ValidateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CateringService implements GenericService<Catering> {

    private final CateringConverter cateringConverter;
    private final ValidateService<Catering> validateService;

    @Override
    public List<Catering> getAllByType(Pageable pageable) {
        return cateringConverter.convertByType(pageable);
    }

    @Override
    public List<Catering> getAllByTypeAndSubType(String subType, Pageable pageable) {

        CateringEnum.getByNameIgnoreCaseOrElseThrow(subType);

        return cateringConverter.convertByTypeAndSubType(subType, pageable);
    }

    @Override
    public Catering saveService(Catering service) {
        validateService.validateTypeAndSubtype(service);
        //TODO
        return null;
    }

    @Override
    public Catering getById(Integer serviceId) {
        return null;
    }

    @Override
    public void deleteService(Integer id) {
        //TODO
    }

    @Override
    public void setIsBooked(Integer serviceId) {

    }

    @Override
    public ServiceType getType() {
        return ServiceType.CATERING;
    }

}