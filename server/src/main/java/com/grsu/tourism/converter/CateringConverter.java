package com.grsu.tourism.converter;

import com.grsu.tourism.model.AbstractService;
import com.grsu.tourism.model.Catering;
import com.grsu.tourism.model.Menu;
import com.grsu.tourism.service.impl.AbstractServiceImpl;
import com.grsu.tourism.service.impl.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.grsu.tourism.enums.ServiceType.CATERING;

@Component
@RequiredArgsConstructor
public class CateringConverter {

    private Map<Integer, List<Menu>> menuMap;

    private final AbstractServiceImpl abstractServiceImpl;
    private final MenuService menuService;

    public Catering convert(AbstractService abstractService) {
        Catering cateringDto = Catering.builder()
                .id(abstractService.getId())
                .name(abstractService.getName())
                .description(abstractService.getDescription())
                .type(abstractService.getType())
                .subType(abstractService.getSubType())
                .price(abstractService.getPrice())
                .isBooked(abstractService.getIsBooked())
                .isActive(abstractService.getIsActive())
                .menuList(menuMap.get(abstractService.getId()))
                .build();
        return cateringDto;
    }

    public List<Catering> convertByType(Pageable pageable) {
        List<AbstractService> abstractServices = abstractServiceImpl.getByType(CATERING, pageable);
        Set<Integer> serviceIds = abstractServiceImpl.getServiceIds(abstractServices);
        menuMap = menuService.getAllMapByServiceIds(serviceIds);

        return abstractServices.stream().map(this::convert).collect(Collectors.toList());
    }

    public List<Catering> convertByTypeAndSubType(String subType, Pageable pageable) {
        List<AbstractService> abstractServices = abstractServiceImpl.getByTypeAndSubType(CATERING, subType, pageable);
        Set<Integer> serviceIds = abstractServiceImpl.getServiceIds(abstractServices);
        menuMap = menuService.getAllMapByServiceIds(serviceIds);

        return abstractServices.stream().map(this::convert).collect(Collectors.toList());
    }
}