package com.grsu.tourism.controller;

import com.grsu.tourism.exception.ApiError;
import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.Facility;
import com.grsu.tourism.model.TourProgram;
import com.grsu.tourism.model.Tourism;
import com.grsu.tourism.service.impl.TourismService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tourism")
public class TourismController extends AbstractServiceController<Tourism> {

    @Autowired
    private TourismService tourismService;

    public TourismController(ServiceFactory<Tourism> serviceFactory) {
        super(serviceFactory);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addTourProgram")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Tourism addTourProgram(@RequestParam("tourId") Integer serviceId, @RequestBody TourProgram tourProgram) {
        return tourismService.saveTourProgram(tourProgram, serviceId);
    }
}