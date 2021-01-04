package com.grsu.tourism.service.impl;

import com.google.common.collect.Lists;
import com.grsu.tourism.model.OpeningHours;
import com.grsu.tourism.repository.OpeningHoursRepository;
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
public class OpeningHoursService {
    private final OpeningHoursRepository openingHoursRepository;

    //поменять чтобы передавались serviceIds по ним находилось и потом делалась мапа
    public Map<Integer, List<OpeningHours>> getAll() {
        List<OpeningHours> openingHours = Lists.newArrayList(openingHoursRepository.findAll());

        Map<Integer, List<OpeningHours>> openingHoursById = new HashMap<>();
        if (!CollectionUtils.isEmpty(openingHours)) {
            openingHoursById = openingHours.stream()
                    .collect(groupingBy(OpeningHours::getServiceId, mapping(row -> row, toList())));
        }
        return openingHoursById;
    }

    public Map<Integer, List<OpeningHours>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<OpeningHours> openingHours = openingHoursRepository.findByServiceIdIn(serviceIds);

        Map<Integer, List<OpeningHours>> openingHoursMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(openingHours)) {
            openingHoursMap = openingHours.stream()
                    .collect(groupingBy(OpeningHours::getServiceId, mapping(row -> row, toList())));
        }
        return openingHoursMap;
    }

    public OpeningHours save(OpeningHours openingHours) {
        return this.openingHoursRepository.save(openingHours);
    }

    public void delete(Integer id) {
        openingHoursRepository.deleteById(id);
    }
}