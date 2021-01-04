package com.grsu.tourism.controller;

import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.exception.ApiError;
import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.service.GenericService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(tags = "Services")
public abstract class AbstractServiceController<S extends AbstractService> {
    private final ServiceFactory<S> serviceFactory;

    @PostMapping("/add")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content =
            @Content(schema = @Schema(implementation = AbstractService.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Type is not found", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "System error", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public S addService(@RequestBody S service) {
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(service.getType());

        GenericService<S> genericService = serviceFactory.getServiceByType(serviceType);
        return genericService.saveService(service);
    }
}
