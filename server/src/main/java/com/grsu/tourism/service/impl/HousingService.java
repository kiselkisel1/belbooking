package com.grsu.tourism.service.impl;

import com.grsu.tourism.enums.HousingEnum;
import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.model.Housing;
import com.grsu.tourism.repository.HousingRepository;
import com.grsu.tourism.service.GenericService;
import com.grsu.tourism.validator.ValidateService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.grsu.tourism.enums.ServiceType.HOUSING;

@Service
@Transactional
@AllArgsConstructor
public class HousingService implements GenericService<Housing> {

    private final HousingRepository housingRepository;
    private final ValidateService<Housing> validateService;

    @Override
    public List<Housing> getAllByType(Pageable pageable) {
        return housingRepository.findByType(HOUSING.getName(), pageable);
    }

    @Override
    public List<Housing> getAllByTypeAndSubType(String subType, Pageable pageable) {

        HousingEnum.getByNameIgnoreCaseOrElseThrow(subType);
        return housingRepository.findByTypeAndSubType(HOUSING.getName(), subType, pageable);
    }

    @Override
    public Housing saveService(Housing service) {
        validateService.validateTypeAndSubtype(service);
        return housingRepository.save(service);
    }

    @SneakyThrows
    @Override
    public Housing getById(Integer serviceId) {
        return housingRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Housing was not found for serviceId " + serviceId));
    }

    @Override
    public void deleteService(Integer id) {
        housingRepository.deleteById(id);
    }

    @Override
    public void setIsBooked(Integer serviceId, Boolean value) {
        Housing service = getById(serviceId);
        service.setIsBooked(value);
        saveService(service);
    }

    @Override
    public ServiceType getType() {
        return HOUSING;
    }

}