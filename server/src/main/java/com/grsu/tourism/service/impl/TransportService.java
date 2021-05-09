package com.grsu.tourism.service.impl;

import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.enums.TransportEnum;
import com.grsu.tourism.model.Housing;
import com.grsu.tourism.model.Transport;
import com.grsu.tourism.repository.TransportRepository;
import com.grsu.tourism.service.GenericService;
import com.grsu.tourism.validator.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.grsu.tourism.enums.ServiceType.TRANSPORT;

@Service
@RequiredArgsConstructor
public class TransportService implements GenericService<Transport> {

    private final TransportRepository transportRepository;
    private final ValidateService<Transport> validateService;

    @Override
    public List<Transport> getAllByType(Pageable pageable) {
        return transportRepository.findByTypeAndIsBooked(TRANSPORT.getName(), pageable,false);
    }

    @Override
    public List<Transport> getAllByTypeAndSubType(String subType, Pageable pageable) {

        TransportEnum.getByNameIgnoreCaseOrElseThrow(subType);
        return transportRepository.findByTypeAndSubTypeAndIsBooked(TRANSPORT.getName(), subType, pageable,false);
    }

    @Override
    public Transport saveService(Transport transport) {
        validateService.validateTypeAndSubtype(transport);
        return transportRepository.save(transport);
    }

    @Override
    public Transport getById(Integer serviceId) {
        Optional<Transport> housing = transportRepository.findById(serviceId);
        return housing.orElseGet(Transport::new);
    }

    @Override
    public void deleteService(Integer id) {
        transportRepository.deleteById(id);
    }

    @Override
    public void setIsBooked(Integer serviceId) {
        Transport service = getById(serviceId);
        service.setIsBooked(true);
        saveService(service);
    }

    @Override
    public ServiceType getType() {
        return TRANSPORT;
    }
}