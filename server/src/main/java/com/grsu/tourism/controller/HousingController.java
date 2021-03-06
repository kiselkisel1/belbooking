package com.grsu.tourism.controller;

import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.Facility;
import com.grsu.tourism.model.Housing;
import com.grsu.tourism.service.impl.FacilityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/housing")
public class HousingController extends AbstractServiceController<Housing> {
    @Autowired
    private FacilityService facilityService;

    public HousingController(ServiceFactory<Housing> serviceFactory) {
        super(serviceFactory);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addFacility")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Housing addService(@RequestParam("serviceId") Integer serviceId, @RequestBody Facility facility) {
        return facilityService.save(facility, serviceId);
    }
}
