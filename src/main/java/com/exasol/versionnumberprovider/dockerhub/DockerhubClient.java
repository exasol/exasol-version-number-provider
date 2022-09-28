package com.exasol.versionnumberprovider.dockerhub;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.exasol.versionnumberprovider.dockerhub.TagList.Tag;

/**
 * This class uses the Docker Hub API to retrieve all tags for the Exasol docker-db image.
 * <p>
 * Note that we don't use request {@code GET /v2/<name>/tags/list} as specified in the
 * <a href="https://docs.docker.com/registry/spec/api/#listing-image-tags">API documentation</a> because this requires
 * authentication. The request we use here does not require authentication but also does not support specifying a custom
 * page size. That's why we always get 10 results per page and need to do multiple requests to retrieve all tags.
 */
public class DockerhubClient {
    private static final Logger LOGGER = Logger.getLogger(DockerhubClient.class.getName());
    private static final String INITIAL_URL = "https://registry.hub.docker.com/v2/repositories/exasol/docker-db/tags";
    private final Deserializer deserializer;
    private final SimpleHttpClient httpClient;

    /**
     * Create a new client for the Docker Hub API.
     */
    public DockerhubClient() {
        deserializer = new Deserializer();
        httpClient = new SimpleHttpClient();
    }

    /**
     * Retrieve all tags of the Exasol docker-db image.
     * 
     * @return all tags.
     */
    public List<TagList.Tag> getAllTags() {
        final List<Tag> tags = new ArrayList<>();
        String nextUrl = INITIAL_URL;
        do {
            final TagList tagList = getTagList(nextUrl);
            tags.addAll(tagList.getTags());
            nextUrl = tagList.getNextUrl();
        } while (nextUrl != null);
        return tags;
    }

    private TagList getTagList(final String url) {
        LOGGER.finest(() -> "Fetching tag list " + url);
        final String response = httpClient.getUrl(URI.create(url));
        return deserializer.deserialize(response);
    }
}
