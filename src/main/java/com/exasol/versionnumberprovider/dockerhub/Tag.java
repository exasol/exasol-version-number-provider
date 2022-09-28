package com.exasol.versionnumberprovider.dockerhub;

import jakarta.json.bind.annotation.JsonbProperty;

/**
 * Represents a tag of a Docker image.
 */
public class Tag {
    @JsonbProperty("name")
    private String name;

    /**
     * Get the name of this tag, e.g. {@code 7.1.14}.
     * 
     * @return name of this tag
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this tag.
     * 
     * @param name name of this tag
     */
    public void setName(final String name) {
        this.name = name;
    }
}
