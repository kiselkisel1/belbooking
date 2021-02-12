package com.grsu.tourism.controller;

import com.grsu.tourism.model.Picture;
import com.grsu.tourism.service.impl.FileStorageService;
import com.grsu.tourism.service.impl.PictureService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;
    private final FileStorageService fileStorageService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Picture addPicture(@RequestParam("file") MultipartFile file,
                              @RequestParam Integer serviceId) {
        return pictureService.save(file, serviceId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getByServiceId/{serviceId}")
    @ApiOperation(value = "Get file by service id", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Map<String, String>> getByServiceId(@PathVariable Integer serviceId) {
        Map<String, String> fileInfos = pictureService.getPathsByServiceId(serviceId);

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getAll")
    @ResponseBody
    @ApiOperation(value = "Get all files", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Map<String, String>> getListFiles() {
        Map<String, String> fileInfos = fileStorageService.loadAll()
                .stream()
                .collect(Collectors.toMap(path -> path.getFileName().toString(), pictureService::getUrl));

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/get/{filename:.+}")
    @ResponseBody
    @ApiOperation(value = "Get file by filename", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{serviceId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public void deletePicture(@PathVariable Integer serviceId) {
        pictureService.deleteByServiceId(serviceId);
    }
}