package com.exasol.versionnumberprovider.dockerhub;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.nio.file.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeserializerTest {

    private Deserializer deserializer;

    @BeforeEach
    void setup() {
        deserializer = new Deserializer();
    }

    @Test
    void deserialize() throws IOException {
        final TagListPage actual = deserialize(Paths.get("src/test/resources/dockerhub_response.json"));
        assertAll(
                () -> assertThat("next url", actual.getNextUrl(),
                        equalTo("https://registry.hub.docker.com/v2/repositories/exasol/docker-db/tags?page=2")),
                () -> assertThat("tags", actual.getTags(), hasSize(10)),
                () -> assertThat("tag 0", actual.getTags().get(0).getName(), equalTo("latest")));
    }

    private TagListPage deserialize(final Path path) throws IOException {
        return deserializer.deserialize(Files.readString(path));
    }
}
