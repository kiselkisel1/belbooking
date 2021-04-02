package com.grsu.tourism.controller;

import com.grsu.tourism.model.Location;
import com.grsu.tourism.model.Region;
import com.grsu.tourism.service.impl.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Location addLocation(@RequestBody Location location) {
        return locationService.save(location);
    }

    @GetMapping("/get")
    public List<Location> getLocations(@RequestParam(defaultValue = "0") Integer pageNumber,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "name") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return locationService.getAll(paging);
    }


    @Secured("ROLE_ADMIN")
    @PostMapping("/region/save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Region saveRegion(@RequestBody Region region) {
        return locationService.save(region);
    }

    @GetMapping("/region/get")
    public List<Region> getRegions(@RequestParam(defaultValue = "0") Integer pageNumber,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(defaultValue = "name") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return locationService.getAllRegions(paging);
    }

    @GetMapping("/region/getById")
    public Region getRegionById(@RequestParam Integer id) {
        return locationService.getRegionById(id);
    }

    @GetMapping("/getById")
    public Location getLocationById(@RequestParam Integer id) {
        return locationService.getLocationById(id);
    }
}