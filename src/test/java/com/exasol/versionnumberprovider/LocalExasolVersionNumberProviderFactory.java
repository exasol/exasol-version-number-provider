package com.exasol.versionnumberprovider;

import java.io.IOException;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonReader;

public class LocalExasolVersionNumberProviderFactory {

    public ExasolVersionNumberProvider getLocalDockerHubResponseDump() throws IOException {
        try (final InputStream jsonStream = getClass().getClassLoader().getResourceAsStream("dockerhub_response.json");
                final JsonReader jsonReader = Json.createReader(jsonStream)) {
            return new ExasolVersionNumberProvider(jsonReader.readArray());
        }
    }
}
