package com.grsu.tourism.service.impl;

import com.grsu.tourism.controller.PictureController;
import com.grsu.tourism.model.Picture;
import com.grsu.tourism.repository.PictureRepository;
import com.grsu.tourism.validator.ValidateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class PictureService {
    private final FileStorageService fileStorageService;
    private final PictureRepository pictureRepository;
    private final ValidateUtil validateUtil;

    public Map<String, String> getPathsByServiceId(Integer serviceId) {
        List<Picture> pictures = pictureRepository.findByServiceIdInAndIsActive(Collections.singleton(serviceId), true);
        if (CollectionUtils.isEmpty(pictures)) {
            return Collections.emptyMap();
        }
        List<String> filenames = pictures.stream()
                .filter(Objects::nonNull)
                .map(Picture::getFilename)
                .collect(toList());
        return fileStorageService.loadAllByFileNames(filenames)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(path -> path.getFileName().toString(), this::getUrl));
    }

    public Picture save(MultipartFile file, Integer serviceId) {
        validateUtil.isServiceExists(serviceId);
        fileStorageService.save(file);

        Picture picture = new Picture();
        picture.setIsActive(true);
        picture.setServiceId(serviceId);
        picture.setFilename(file.getOriginalFilename());
        return this.pictureRepository.save(picture);
    }

    public void deleteByServiceId(Integer serviceId) {
        List<Picture> pictures = pictureRepository.findByServiceIdInAndIsActive(Collections.singleton(serviceId), true);
        if (CollectionUtils.isEmpty(pictures)) {
            return;
        }
        pictures.stream()
                .filter(Objects::nonNull)
                .forEach(picture -> {
                    pictureRepository.deleteById(picture.getId());
                    fileStorageService.deleteByFilename(picture.getFilename());
                });
    }

    public String getUrl(Path path) {
        String url = MvcUriComponentsBuilder
                .fromMethodName(PictureController.class, "getFile", path.getFileName().toString()).build().toString();
        return url;
    }
}
