package com.grsu.tourism.service.impl;


import com.google.common.collect.Lists;
import com.grsu.tourism.model.Location;
import com.grsu.tourism.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Map<Integer, List<Location>> getAll() {
        List<Location> locations = Lists.newArrayList(locationRepository.findAll());

        Map<Integer, List<Location>> locationByServiceId = new HashMap<>();
        if (!CollectionUtils.isEmpty(locations)) {
            locationByServiceId = locations.stream()
                    .collect(groupingBy(Location::getServiceId, mapping(row -> row, toList())));
        }
        return locationByServiceId;
    }

    public Map<Integer, List<Location>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<Location> locations = locationRepository.findByIdIn(serviceIds);

        Map<Integer, List<Location>> locationMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(locations)) {
            locationMap = locations.stream()
                    .collect(groupingBy(Location::getServiceId, mapping(row -> row, toList())));
        }
        return locationMap;
    }

    public Location save(Location location) {
        return this.locationRepository.save(location);
    }
}
