package com.grsu.tourism.controller;

import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.service.GenericService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public abstract class AbstractServiceController<S extends AbstractService> {
    private final ServiceFactory<S> serviceFactory;

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    @ApiOperation(value = "addService", authorizations = {@Authorization(value = "jwtToken")})
    public S addService(@RequestBody S service) {
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(service.getType());

        GenericService<S> genericService = serviceFactory.getServiceByType(serviceType);
        return genericService.saveService(service);
    }
}
