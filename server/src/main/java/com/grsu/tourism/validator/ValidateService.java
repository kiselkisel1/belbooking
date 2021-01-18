package com.grsu.tourism.validator;

import com.grsu.tourism.enums.*;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.repository.AbstractServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ValidateService<T extends AbstractService> {
    public void validateTypeAndSubtype(T t) {
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(t.getType());
        switch (serviceType) {
            case HOUSING:
                HousingEnum.getByNameIgnoreCaseOrElseThrow(t.getSubType());
                break;
            case TRANSPORT:
                TransportEnum.getByNameIgnoreCaseOrElseThrow(t.getSubType());
                break;
            case TOURISM:
                TourismEnum.getByNameIgnoreCaseOrElseThrow(t.getSubType());
                break;
            case CATERING:
                CateringEnum.getByNameIgnoreCaseOrElseThrow(t.getSubType());
                break;
            case ATTRACTIONS:
                AttractionsEnum.getByNameIgnoreCaseOrElseThrow(t.getSubType());
                break;
        }
    }
}
