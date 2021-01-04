package com.grsu.tourism.controller;

import com.grsu.tourism.exception.ApiError;
import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.Facility;
import com.grsu.tourism.model.TourProgram;
import com.grsu.tourism.model.Tourism;
import com.grsu.tourism.service.impl.TourismService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tourism")
public class TourismController extends AbstractServiceController<Tourism> {

    @Autowired
    private TourismService tourismService;

    public TourismController(ServiceFactory<Tourism> serviceFactory) {
        super(serviceFactory);
    }

    @PostMapping("/addTourProgram")
    @Operation(summary = "Add tour program")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content =
            @Content(schema = @Schema(implementation = Facility.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Type is not found", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "System error", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public Tourism addTourProgram(@RequestParam("tourId") Integer serviceId, @RequestBody TourProgram tourProgram) {
        return tourismService.saveTourProgram(tourProgram, serviceId);
    }
}