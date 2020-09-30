package com.exasol.versionnumberprovider;

import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonReader;

public class LocalExasolVersionNumberProviderFactory {

    public ExasolVersionNumberProvider getForLocalDockerGubResponseDump() throws IOException {
        try (final InputStream jsonStream = getClass().getClassLoader().getResourceAsStream("dockerhub_response.json");
                final JsonReader jsonReader = Json.createReader(jsonStream)) {
            return new ExasolVersionNumberProvider(jsonReader.readArray());
        }
    }
}
