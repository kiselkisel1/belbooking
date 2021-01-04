package com.grsu.tourism.service.impl;

import com.google.common.collect.Lists;
import com.grsu.tourism.model.Picture;
import com.grsu.tourism.repository.PictureRepository;
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
public class PictureService {
    private final PictureRepository pictureRepository;

    public Map<Integer, List<Picture>> getAll() {
        List<Picture> locations = Lists.newArrayList(pictureRepository.findAll());

        Map<Integer, List<Picture>> locationByServiceId = new HashMap<>();
        if (!CollectionUtils.isEmpty(locations)) {
            locationByServiceId = locations.stream()
                    .collect(groupingBy(Picture::getServiceId, mapping(row -> row, toList())));
        }
        return locationByServiceId;
    }

    public Map<Integer, List<Picture>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<Picture> openingHours = pictureRepository.findByServiceIdIn(serviceIds);

        Map<Integer, List<Picture>> pictureMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(openingHours)) {
            pictureMap = openingHours.stream()
                    .collect(groupingBy(Picture::getServiceId, mapping(row -> row, toList())));
        }
        return pictureMap;
    }

    public Picture save(Picture picture) {
        return this.pictureRepository.save(picture);
    }

    public void delete(Integer id) {
        pictureRepository.deleteById(id);
    }
}