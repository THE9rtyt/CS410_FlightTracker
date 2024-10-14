package com.data_objects;

import java.util.Objects;

public record Resource(int id, String data) {
    public Resource {
        Objects.requireNonNull(id);
        Objects.requireNonNull(data);
    }
}
