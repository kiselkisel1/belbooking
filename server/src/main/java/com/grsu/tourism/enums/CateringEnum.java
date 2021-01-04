package com.grsu.tourism.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum CateringEnum {

    RESTAURANT("restaurant"),
    CAFE("cafe"),
    BAR("bar");

    private final String name;

    CateringEnum(String name) {
        this.name = name;
    }

    public static Optional<CateringEnum> getByNameIgnoreCase(String name) {
        return Arrays.stream(CateringEnum.values())
                .filter(s -> s.name.equalsIgnoreCase(name))
                .findFirst();
    }

    public static CateringEnum getByNameIgnoreCaseOrElseThrow(String name) {
        return getByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No subtype was found for catering: %s", name)));
    }
}