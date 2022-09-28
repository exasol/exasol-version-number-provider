package com.exasol.versionnumberprovider.dockerhub;

import java.util.List;

import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;

/**
 * Represents the paginated response of the Dockerhub API for a list of tags.
 */
public class TagListPage {

    @JsonbProperty("next")
    @JsonbNillable
    private String nextUrl;
    @JsonbProperty("results")
    private List<Tag> tags;

    /**
     * Get the URL of the next page or {@code null} if no next page is available.
     * 
     * @return URL of the next page
     */
    public String getNextUrl() {
        return nextUrl;
    }

    /**
     * Get the tags in this page.
     * 
     * @return tags in this page
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Set the URL of the next page.
     * 
     * @param nextUrl URL of the next page
     */
    public void setNextUrl(final String nextUrl) {
        this.nextUrl = nextUrl;
    }

    /**
     * Set the tags in this page.
     * 
     * @param tags tags in this page
     */
    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
