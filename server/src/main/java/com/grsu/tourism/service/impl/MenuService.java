package com.grsu.tourism.service.impl;

import com.grsu.tourism.model.Menu;
import com.grsu.tourism.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Map<Integer, List<Menu>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<Menu> menus = menuRepository.findByServiceIdIn(serviceIds);

        Map<Integer, List<Menu>> menuByServiceIds = new HashMap<>();
        if (!CollectionUtils.isEmpty(menus)) {
            menuByServiceIds = menus.stream()
                    .collect(groupingBy(Menu::getServiceId, mapping(row -> row, toList())));
        }
        return menuByServiceIds;
    }
}
