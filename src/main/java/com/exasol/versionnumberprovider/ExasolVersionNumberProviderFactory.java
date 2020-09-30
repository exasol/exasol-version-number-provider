package com.exasol.versionnumberprovider;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonReader;

/**
 * Factory for {@link ExasolVersionNumberProvider}.
 * <p>
 * This factory fetches the list of release tags from the internet.
 * </p>
 */
public class ExasolVersionNumberProviderFactory {
    private static final String TAGS_API_ENDPOINT = "https://registry.hub.docker.com/v1/repositories/exasol/docker-db/tags";

    /**
     * Build an {@link ExasolVersionNumberProvider} using a tag list from docker hub.
     * 
     * @return built {@link ExasolVersionNumberProvider}.
     * @throws IOException if connection failed
     */
    public ExasolVersionNumberProvider buildExasolVersionNumberProvider() throws IOException {
        try (final InputStream inputStream = new URL(TAGS_API_ENDPOINT).openStream();
                final JsonReader jsonReader = Json.createReader(inputStream)) {
            return new ExasolVersionNumberProvider(jsonReader.readArray());
        } catch (final MalformedURLException exception) {
            // should never happen, since ExasolVersionNumberProvider is well formed
            throw new IllegalStateException("Internal error.", exception);
        }
    }
}
