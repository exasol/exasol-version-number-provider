package com.exasol.versionnumberprovider.dockerhub;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class Deserializer {
    private final Jsonb jsonb;

    public Deserializer() {
        this.jsonb = JsonbBuilder.create();
    }

    public TagList deserialize(final String content) {
        return jsonb.fromJson(content, TagList.class);
    }
}
