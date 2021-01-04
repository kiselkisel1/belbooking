package com.grsu.tourism.controller;

import com.grsu.tourism.exception.ApiError;
import com.grsu.tourism.model.OpeningHours;
import com.grsu.tourism.model.Picture;
import com.grsu.tourism.service.impl.PictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;

    @PostMapping("/save")
    @Operation(summary = "Add picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content =
            @Content(schema = @Schema(implementation = Picture.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Type is not found", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "System error", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public Picture addPicture(@RequestBody Picture picture) {
        return pictureService.save(picture);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content =
            @Content(schema = @Schema(implementation = OpeningHours.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Type is not found", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "System error", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public void deletePicture(@RequestParam Integer id) {
        pictureService.delete(id);
    }
}