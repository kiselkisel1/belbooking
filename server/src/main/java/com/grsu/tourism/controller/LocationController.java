package com.grsu.tourism.controller;//package com.grsu.tourism.controller;
//
//import com.grsu.tourism.exception.ApiError;
//import com.grsu.tourism.model.Location;
//import com.grsu.tourism.model.OpeningHours;
//import com.grsu.tourism.service.impl.LocationService;
//import com.grsu.tourism.service.impl.OpeningHoursService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/locations")
//public class LocationController {
//
//    private final LocationService locationService;
//
//    @PostMapping("/save")
//    @Operation(summary = "Add location")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Item found", content =
//            @Content(schema = @Schema(implementation = Location.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
//            @ApiResponse(responseCode = "404", description = "Type is not found", content =
//            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
//            @ApiResponse(responseCode = "500", description = "System error", content =
//            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
//    public Location addLocation(@RequestBody Location location) {
//        return locationService.save(location);
//    }
//}