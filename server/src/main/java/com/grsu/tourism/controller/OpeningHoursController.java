package com.grsu.tourism.controller;

import com.grsu.tourism.model.OpeningHours;
import com.grsu.tourism.service.impl.OpeningHoursService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/openingHours")
public class OpeningHoursController {

    private final OpeningHoursService openingHoursService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public OpeningHours addOpeningHours(@RequestBody OpeningHours openingHours) {
        return openingHoursService.save(openingHours);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public void deleteOpeningHours(@RequestParam Integer id) {
        openingHoursService.delete(id);
    }
}