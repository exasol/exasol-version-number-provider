package com.exasol.versionnumberprovider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;

class ExasolVersionNumberProviderFactoryTest {
    @Test
    void test() {
        final ExasolVersionNumberProvider versionNumberProvider = new ExasolVersionNumberProviderFactory()
                .buildExasolVersionNumberProvider();
        final List<String> allReleaseNumbers = versionNumberProvider.getAllReleaseNumbers();
        assertAll(
                () -> assertThat(allReleaseNumbers,
                        hasItems("6.2.0-d1", "7.0.0", "7.0.1", "7.0.2", "7.1.14", "8.29.11", "2025.1.3")),
                () -> assertThat(allReleaseNumbers, hasSize(greaterThanOrEqualTo(101))));
    }
}
