package com.grsu.tourism.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum TourismEnum {

    BUS_EXCURSIONS("bus-excursions"),
    INDIVIDUAL_EXCURSIONS("individual-excursions"),
    BIKE_EXCURSIONS("bike-excursions"),
    AGROTOURISM("agrotourism");

    private final String name;

    TourismEnum(String name) {
        this.name = name;
    }

    public static Optional<TourismEnum> getByNameIgnoreCase(String name) {
        return Arrays.stream(TourismEnum.values())
                .filter(s -> s.name.equalsIgnoreCase(name))
                .findFirst();
    }

    public static TourismEnum getByNameIgnoreCaseOrElseThrow(String name) {
        return getByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No subtype was found for tourism: %s", name)));
    }
}