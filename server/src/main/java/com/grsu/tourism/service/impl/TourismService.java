package com.grsu.tourism.service.impl;

import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.enums.TourismEnum;
import com.grsu.tourism.model.TourProgram;
import com.grsu.tourism.model.Tourism;
import com.grsu.tourism.repository.TourismRepository;
import com.grsu.tourism.service.GenericService;
import com.grsu.tourism.validator.ValidateService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.grsu.tourism.enums.ServiceType.TOURISM;

@Service
@Transactional
@AllArgsConstructor
public class TourismService implements GenericService<Tourism> {

    private final TourismRepository tourismRepository;
    private final ValidateService<Tourism> validateService;

    @Override
    public List<Tourism> getAllByType(Pageable pageable) {
        return tourismRepository.findByType(TOURISM.getName(), pageable);
    }

    @Override
    public List<Tourism> getAllByTypeAndSubType(String subType, Pageable pageable) {

        TourismEnum.getByNameIgnoreCaseOrElseThrow(subType);
        return tourismRepository.findByTypeAndSubType(TOURISM.getName(), subType, pageable);
    }

    @Override
    public Tourism saveService(Tourism service) {
        validateService.validateTypeAndSubtype(service);
        return tourismRepository.save(service);
    }

    @SneakyThrows
    @Override
    public Tourism getById(Integer serviceId) {
        return tourismRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Tourism has not found for serviceId " + serviceId));
    }

    public Tourism saveTourProgram(TourProgram tourProgram, Integer serviceId) {
        Tourism tourism = getById(serviceId);
        tourism.getTourPrograms().add(tourProgram);
        return saveService(tourism);
    }

    @Override
    public ServiceType getType() {
        return TOURISM;
    }
}
