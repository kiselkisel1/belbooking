package com.grsu.tourism.service.impl;


import com.grsu.tourism.model.Location;
import com.grsu.tourism.model.Region;
import com.grsu.tourism.repository.LocationRepository;
import com.grsu.tourism.repository.RegionRepository;
import com.grsu.tourism.validator.ValidateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final RegionRepository regionRepository;
    private final ValidateUtil validateService;

    public Map<Integer, List<Location>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<Location> locations = locationRepository.findByIdIn(serviceIds);

        Map<Integer, List<Location>> locationMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(locations)) {
            locationMap = locations.stream()
                    .collect(groupingBy(Location::getServiceId, mapping(row -> row, toList())));
        }
        return locationMap;
    }

    public List<Location> getAll(Pageable pageable) {
        Page<Location> pagedResult = locationRepository.findAll(pageable);

        List<Location> locations = new ArrayList<>();
        if (pagedResult.hasContent()) {
            locations = pagedResult.getContent();
        }
        return locations;
    }

    //add validation if serviceId exists
    public Location save(Location location) {
        validateService.isServiceExists(location.getServiceId());
        return this.locationRepository.save(location);
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public List<Region> getAllRegions(Pageable pageable) {
        Page<Region> pagedResult = regionRepository.findAll(pageable);

        List<Region> locations = new ArrayList<>();
        if (pagedResult.hasContent()) {
            locations = pagedResult.getContent();
        }
        return locations;
    }

    public Region getRegionById(Integer id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Region was not found for id " + id));
    }

    public Location getLocationById(Integer id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location was not found for id " + id));
    }
}
