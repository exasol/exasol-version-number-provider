package com.exasol.versionnumberprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ExasolVersionNumberProviderFactoryTest {

    @Test
    void test() throws IOException {
        final ExasolVersionNumberProvider versionNumberProvider = new ExasolVersionNumberProviderFactory()
                .buildExasolVersionNumberProvider();
        assertThat(versionNumberProvider.getAllReleaseNumbers(), hasItems("7.0.0", "7.0.1", "7.0.2"));
    }
}