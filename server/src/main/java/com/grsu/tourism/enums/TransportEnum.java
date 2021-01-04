package com.grsu.tourism.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum TransportEnum {

    CAR("car"),
    MOTO("moto");

    private final String name;

    TransportEnum(String name) {
        this.name = name;
    }

    public static Optional<TransportEnum> getByNameIgnoreCase(String name) {
        return Arrays.stream(TransportEnum.values())
                .filter(s -> s.name.equalsIgnoreCase(name))
                .findFirst();
    }

    public static TransportEnum getByNameIgnoreCaseOrElseThrow(String name) {
        return getByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalArgumentException(String.format("No subtype was found for transport: %s", name)));
    }
}