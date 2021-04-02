package com.grsu.tourism.controller;

import com.grsu.tourism.converter.ServiceConverter;
import com.grsu.tourism.dto.ServiceDto;
import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.service.GenericService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServiceController {
    private final ServiceConverter serviceConverter;
    private final ServiceFactory serviceFactory;

    @GetMapping("/type")
    public List<ServiceDto> getServices(@RequestParam String type,
                                        @RequestParam(defaultValue = "0") Integer pageNumber,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "price") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(type);

        GenericService genericService = serviceFactory.getServiceByType(serviceType);
        List services = genericService.getAllByType(paging);
        List<ServiceDto> serviceDtos = serviceConverter.convert(services);
        return serviceDtos;
    }


    @GetMapping("/subType")
    public List<ServiceDto> getServicesBySubType(@RequestParam String type,
                                                 @RequestParam String subType,
                                                 @RequestParam(defaultValue = "0") Integer pageNumber,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "price") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(type);

        GenericService genericService = serviceFactory.getServiceByType(serviceType);
        List services = genericService.getAllByTypeAndSubType(subType, paging);
        return serviceConverter.convert(services);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete")
    @ApiOperation(value = "deleteService", authorizations = {@Authorization(value = "jwtToken")})
    public void deleteService(@RequestParam Integer id, @RequestParam String type) {
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(type);
        GenericService genericService = serviceFactory.getServiceByType(serviceType);
        genericService.deleteService(id);
    }
}