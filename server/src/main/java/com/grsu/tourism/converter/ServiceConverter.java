package com.grsu.tourism.converter;

import com.grsu.tourism.dto.ServiceDto;
import com.grsu.tourism.model.*;
import com.grsu.tourism.repository.StockRepository;
import com.grsu.tourism.service.impl.ContactDetailsService;
import com.grsu.tourism.service.impl.LocationService;
import com.grsu.tourism.service.impl.OpeningHoursService;
import com.grsu.tourism.service.impl.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Component
@AllArgsConstructor
public class ServiceConverter implements Converter<AbstractService, ServiceDto> {

    private final LocationService locationService;
    private final ContactDetailsService contactDetailsService;
    private final PictureService pictureService;
    private final OpeningHoursService openingHoursService;
    private final StockRepository stockRepository;

    private Map<Integer, List<Location>> locationMap;
    private Map<Integer, List<ContactDetails>> contactDetailsMap;
    private Map<Integer, List<Stock>> stockMap;
    private Map<Integer, List<OpeningHours>> openingHoursMap;

    @Override
    public ServiceDto convert(AbstractService source) {
        Integer serviceId = source.getId();
        ServiceDto serviceDto = ServiceDto.builder()
                .service(source)
                .locations(locationMap.get(serviceId))
                .contactDetails(contactDetailsMap.get(serviceId))
                .stocks(stockMap.get(serviceId))
                .pictures(pictureService.getPathsByServiceId(serviceId))
                .openingHours(openingHoursMap.get(serviceId))
                .build();
        return serviceDto;
    }

    public List<ServiceDto> convert(List<? extends AbstractService> source) {
        Set<Integer> serviceIds = source.stream().map(AbstractService::getId).collect(toSet());

        locationMap = locationService.getAllMapByServiceIds(serviceIds);
        contactDetailsMap = contactDetailsService.getAllMapByServiceIds(serviceIds);
        stockMap = getStockMapByServiceIds(serviceIds);
        openingHoursMap = openingHoursService.getAllMapByServiceIds(serviceIds);

        return source.stream().map(this::convert).collect(Collectors.toList());
    }

    public Map<Integer, List<Stock>> getStockMapByServiceIds(Collection<Integer> serviceIds) {
        List<Stock> stocks = stockRepository.findByServiceIdIn(serviceIds);

        Map<Integer, List<Stock>> stockMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(stocks)) {
            stockMap = stocks.stream()
                    .collect(groupingBy(Stock::getServiceId, mapping(row -> row, toList())));
        }
        return stockMap;
    }
}
