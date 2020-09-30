package com.exasol.versionnumberprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ExasolVersionNumberProviderTest {

    @Test
    void testGetLatest() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = new LocalExasolVersionNumberProviderFactory()
                .getLocalDockerHubResponseDump();
        assertThat(versionNumberProvider.getLatestReleaseNumber(), equalTo("7.0.2"));
    }
}