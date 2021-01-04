package com.grsu.tourism.controller;

import com.grsu.tourism.exception.ApiError;
import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.Facility;
import com.grsu.tourism.model.Housing;
import com.grsu.tourism.service.impl.FacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/housing")
public class HousingController extends AbstractServiceController<Housing> {
    @Autowired
    private FacilityService facilityService;

    public HousingController(ServiceFactory<Housing> serviceFactory) {
        super(serviceFactory);
    }

    @PostMapping("/addFacility")
    @Operation(summary = "Get services by type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content =
            @Content(schema = @Schema(implementation = Facility.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Type is not found", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "System error", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public Housing addService(@RequestParam("serviceId") Integer serviceId, @RequestBody Facility facility) {
        return facilityService.save(facility, serviceId);
    }
}
