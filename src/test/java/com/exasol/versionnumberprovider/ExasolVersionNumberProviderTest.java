package com.exasol.versionnumberprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ExasolVersionNumberProviderTest {
    public static final LocalExasolVersionNumberProviderFactory FACTORY = new LocalExasolVersionNumberProviderFactory();

    @Test
    void testGetLatest() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = FACTORY.getLocalDockerHubResponseDump();
        final String latestVersionString = versionNumberProvider.getLatestReleaseNumber();
        assertThat(latestVersionString, equalTo("7.0.2"));
    }

    @Test
    void testGetLatestForMajor() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = FACTORY.getLocalDockerHubResponseDump();
        assertThat(versionNumberProvider.getLatestReleaseForMajor(6), equalTo("6.2.10-d1"));
    }

    @Test
    void testGetLatestForMinor() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = FACTORY.getLocalDockerHubResponseDump();
        assertThat(versionNumberProvider.getLatestReleaseForMinor(6, 1), equalTo("6.1.12-d1"));
    }
}