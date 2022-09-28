package com.exasol.versionnumberprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class ExasolVersionNumberProviderTest {
    private static final LocalExasolVersionNumberProviderFactory FACTORY = new LocalExasolVersionNumberProviderFactory();
    private static final Path RESPONSE_FILE = Paths.get("src/test/resources/simple_dockerhub_response.json");

    @Test
    void testGetLatest() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = FACTORY.getLocalDockerHubResponseDump(RESPONSE_FILE);
        final String latestVersionString = versionNumberProvider.getLatestReleaseNumber();
        assertThat(latestVersionString, equalTo("7.0.2"));
    }

    @Test
    void testGetLatestForMajor() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = FACTORY.getLocalDockerHubResponseDump(RESPONSE_FILE);
        assertThat(versionNumberProvider.getLatestReleaseForMajor(6), equalTo("6.2.10-d1"));
    }

    @Test
    void testGetLatestForMinor() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = FACTORY.getLocalDockerHubResponseDump(RESPONSE_FILE);
        assertThat(versionNumberProvider.getLatestReleaseForMinor(6, 1), equalTo("6.1.12-d1"));
    }
}
