package com.exasol.versionnumberprovider.dockerhub;

import java.util.List;

import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;

public class TagList {

    @JsonbProperty("next")
    @JsonbNillable
    private String nextUrl;
    @JsonbProperty("results")
    private List<Tag> tags;

    public String getNextUrl() {
        return nextUrl;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setNextUrl(final String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public static class Tag {
        @JsonbProperty("name")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }
    }
}
