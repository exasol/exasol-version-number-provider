package com.exasol.versionnumberprovider.dockerhub;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * This class deserializes a JSON {@link String} to a {@link TagListPage}.
 */
public class Deserializer {
    private final Jsonb jsonb;

    /**
     * Create a new {@link Deserializer}.
     */
    public Deserializer() {
        this.jsonb = JsonbBuilder.create();
    }

    /**
     * Deserialize the given JSON content to a {@link TagListPage} object.
     * 
     * @param content JSON content to deserialize
     * @return deserialized object
     */
    public TagListPage deserialize(final String content) {
        return jsonb.fromJson(content, TagListPage.class);
    }
}
