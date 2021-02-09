package com.grsu.tourism.controller;

import com.grsu.tourism.model.Picture;
import com.grsu.tourism.service.impl.PictureService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Picture addPicture(@RequestParam("file") MultipartFile file,
                              @RequestParam Integer serviceId) {
        return pictureService.save(file, serviceId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getByServiceId")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<Resource> getByServiceId(@RequestParam Integer serviceId) {
        return pictureService.getResourceByServiceId(serviceId);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public void deletePicture(@RequestParam Integer id) {
        pictureService.delete(id);
    }
}