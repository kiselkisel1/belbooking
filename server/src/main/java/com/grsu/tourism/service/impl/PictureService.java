package com.grsu.tourism.service.impl;

import com.grsu.tourism.model.Picture;
import com.grsu.tourism.repository.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@AllArgsConstructor
public class PictureService {
    private final FileStorageService fileStorageService;
    private final PictureRepository pictureRepository;

    public Map<Integer, List<Picture>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<Picture> pictures = pictureRepository.findByServiceIdInAndIsActive(serviceIds, true);

        Map<Integer, List<Picture>> pictureMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(pictures)) {
            pictureMap = pictures.stream()
                    .collect(groupingBy(Picture::getServiceId, mapping(row -> row, toList())));
        }
        return pictureMap;
    }

    public Map<Integer, List<Resource>> getAllResourcesByServiceIds(Collection<Integer> serviceIds) {
        List<Picture> pictures = pictureRepository.findByServiceIdInAndIsActive(serviceIds, true);
        if (CollectionUtils.isEmpty(pictures)) {
            return Collections.emptyMap();
        }
        Map<Integer, List<Resource>> resourceMap = pictures.stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(Picture::getServiceId, mapping(picture -> fileStorageService.load(picture.getPictureUrl()), toList())));
        return resourceMap;
    }

    public List<Resource> getResourceByServiceId(Integer serviceId) {
        List<Picture> pictures = pictureRepository.findByServiceIdInAndIsActive(Collections.singleton(serviceId), true);
        if (CollectionUtils.isEmpty(pictures)) {
            return Collections.emptyList();
        }
        List<Resource> resources = pictures.stream()
                .filter(Objects::nonNull)
                .map(picture -> fileStorageService.load(picture.getPictureUrl()))
                .collect(toList());
        return resources;
    }

    public Picture save(MultipartFile file, Integer serviceId) {
        fileStorageService.save(file);

        Picture picture = new Picture();
        picture.setIsActive(true);
        picture.setServiceId(serviceId);
        picture.setPictureUrl(file.getOriginalFilename());
        return this.pictureRepository.save(picture);
    }

    public void delete(Integer id) {
        pictureRepository.deleteById(id);
    }
}
