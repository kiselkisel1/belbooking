package com.grsu.tourism.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum AttractionsEnum {

    CULTURE("culture"),
    RELIGION("religion"),
    ARCHITECTURE("architecture"),
    ENTERTAINMENT("entertainment");

    private final String name;

    AttractionsEnum(String name) {
        this.name = name;
    }

    public static Optional<AttractionsEnum> getByNameIgnoreCase(String name) {
        return Arrays.stream(AttractionsEnum.values())
                .filter(s -> s.name.equalsIgnoreCase(name))
                .findFirst();
    }

    public static AttractionsEnum getByNameIgnoreCaseOrElseThrow(String name) {
        return getByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No subtype was found for attraction: %s", name)));
    }
}