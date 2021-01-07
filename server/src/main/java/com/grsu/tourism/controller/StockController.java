package com.grsu.tourism.controller;

import com.grsu.tourism.dto.StockDto;
import com.grsu.tourism.model.Stock;
import com.grsu.tourism.service.impl.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    //TODO check if beginDate is today or next day but not date that is old
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/get")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<StockDto> getStocks(@RequestParam(defaultValue = "0") Integer pageNumber,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "discount") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return stockService.getAll(paging);
    }

    @PostMapping("/save")
    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @DeleteMapping("/delete")
    @Secured("ROLE_ADMIN")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public void deleteStock(@RequestParam Integer id) {
        stockService.delete(id);
    }
}