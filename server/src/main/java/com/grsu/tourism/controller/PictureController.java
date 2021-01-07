package com.grsu.tourism.controller;

import com.grsu.tourism.model.Picture;
import com.grsu.tourism.service.impl.PictureService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Picture addPicture(@RequestBody Picture picture) {
        return pictureService.save(picture);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public void deletePicture(@RequestParam Integer id) {
        pictureService.delete(id);
    }
}