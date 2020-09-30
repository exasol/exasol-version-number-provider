package com.exasol.versionnumberprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ExasolVersionNumberProviderTest {
    static final ExasolVersionNumber KNOWN_LATEST = new ExasolVersionNumber("7.0.2");

    @Test
    void testGetLatest() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = new LocalExasolVersionNumberProviderFactory()
                .getLocalDockerHubResponseDump();

        final String latestVersionString = versionNumberProvider.getLatestReleaseNumber();
        assertThat(latestVersionString, equalTo(KNOWN_LATEST.toString()));

        final ExasolVersionNumber latest_7 = versionNumberProvider.getLatestReleaseForMajor(7);
        // until Exasol releases 7.1 or 8.0...
        assertThat(latest_7, equalTo(KNOWN_LATEST));
        assertThat(latest_7.getMajorVersion(), equalTo(7));

        final ExasolVersionNumber latest_7_0 = versionNumberProvider.getLatestReleaseForMinor(7, 0);
        assertThat(latest_7_0, equalTo(latest_7));

        final ExasolVersionNumber latest_6 = versionNumberProvider.getLatestReleaseForMajor(6);
        final ExasolVersionNumber latest_6_1 = versionNumberProvider.getLatestReleaseForMinor(6, 1);
        final ExasolVersionNumber latest_6_2 = versionNumberProvider.getLatestReleaseForMinor(6, 2);

        assertThat(latest_6, greaterThan(latest_6_1));
        assertThat(latest_6_2, greaterThan(latest_6_1));
        // there will not be a 6.3
        assertThat(latest_6, equalTo(latest_6_2));
    }
}