package com.swapper.enums;

import java.util.Arrays;
import java.util.Objects;

public enum Role {

    USER(1);

    private final Integer id;

    Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Role fromId(Integer id) {
        return Arrays.stream(Role.values())
            .filter(role -> Objects.equals(role.getId(), id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown role: " + id));
    }
}