package com.grsu.tourism.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ServiceType {

    HOUSING("housing"),
    TOURISM("tourism"),
    TRANSPORT("transport"),
    CATERING("catering"),
    ATTRACTIONS("attractions");

    private final String name;

    ServiceType(String name) {
        this.name = name;
    }

    public static Optional<ServiceType> getByNameIgnoreCase(String name) {
        return Arrays.stream(ServiceType.values())
                .filter(s -> s.name.equalsIgnoreCase(name))
                .findFirst();
    }

    public static ServiceType getByNameIgnoreCaseOrElseThrow(String name) {
        return getByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException("No service type found for type:" + name));
    }
}
