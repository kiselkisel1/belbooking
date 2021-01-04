package com.grsu.tourism.converter;

import com.grsu.tourism.dto.ServiceDto;
import com.grsu.tourism.dto.StockDto;
import com.grsu.tourism.model.Stock;
import com.grsu.tourism.service.impl.AbstractServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StockConverter implements Converter<Stock, StockDto> {

    private AbstractServiceImpl abstractServiceImpl;
    private Map<Integer, ServiceDto> serviceMap;

    @Override
    public StockDto convert(Stock source) {
        StockDto stock = StockDto.builder()
                .id(source.getId())
                .beginDate(source.getBeginDate())
                .discount(source.getDiscount())
                .endDate(source.getEndDate())
                .service(serviceMap.get(source.getServiceId()))
                .build();
        return stock;
    }

    public List<StockDto> convert(List<Stock> source) {
        serviceMap = abstractServiceImpl.getAllMap();

        return source.stream().map(this::convert).collect(Collectors.toList());
    }
}
