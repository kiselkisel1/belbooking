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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServiceController {
    private final ServiceConverter serviceConverter;
    private final ServiceFactory serviceFactory;

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @GetMapping("/type")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
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


    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @GetMapping("/subType")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
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
}