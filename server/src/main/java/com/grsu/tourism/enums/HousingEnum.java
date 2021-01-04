package com.grsu.tourism.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum HousingEnum {

    HOTEL("hotel"),
    FLAT("flat"),
    COUNTRY_HOUSES("country-houses");

    private final String name;

    HousingEnum(String name) {
        this.name = name;
    }

    public static Optional<HousingEnum> getByNameIgnoreCase(String name) {
        return Arrays.stream(HousingEnum.values())
                .filter(s -> s.name.equalsIgnoreCase(name))
                .findFirst();
    }

    public static HousingEnum getByNameIgnoreCaseOrElseThrow(String name) {
        return getByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No subtype was found for housing: %s", name)));
    }
}
