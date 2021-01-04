package com.grsu.tourism.controller;

import com.grsu.tourism.dto.StockDto;
import com.grsu.tourism.exception.ApiError;
import com.grsu.tourism.model.Stock;
import com.grsu.tourism.service.impl.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    //TODO check if beginDate is today or next day but not date that is old
    @GetMapping("/get")
    @Operation(summary = "Get stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content = {
                    @Content(schema = @Schema(implementation = Stock.class), mediaType = MediaType.APPLICATION_JSON_VALUE),
                    @Content(schema = @Schema(implementation = Stock.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
    })
    public List<StockDto> getStocks(@RequestParam(defaultValue = "0") Integer pageNumber,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "discount") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return stockService.getAll(paging);
    }

    @PostMapping("/save")
    @Operation(summary = "Add stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content =
            @Content(schema = @Schema(implementation = Stock.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Type is not found", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "System error", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found", content =
            @Content(schema = @Schema(implementation = Stock.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Type is not found", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "System error", content =
            @Content(schema = @Schema(implementation = ApiError.class), mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public void deleteStock (@RequestParam Integer id) {
        stockService.delete(id);
    }
}