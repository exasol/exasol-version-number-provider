package com.exasol.versionnumberprovider;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ExasolVersionNumberTest {
    private static final ExasolVersionNumber RELEASE_600 = new ExasolVersionNumber("6.0.0");
    private static final ExasolVersionNumber RELEASE_700 = new ExasolVersionNumber("7.0.0");
    private static final ExasolVersionNumber RELEASE_610 = new ExasolVersionNumber("6.1.0");
    private static final ExasolVersionNumber RELEASE_601 = new ExasolVersionNumber("6.0.1");
    private static final ExasolVersionNumber RELEASE_600D1 = new ExasolVersionNumber("6.0.0-d1");
    private static final ExasolVersionNumber RELEASE_602 = new ExasolVersionNumber("6.0.2");
    private static final ExasolVersionNumber RELEASE_800 = new ExasolVersionNumber("8.0.0");
    private static final ExasolVersionNumber RELEASE_82913 = new ExasolVersionNumber("8.29.13");
    private static final ExasolVersionNumber RELEASE_202513 = new ExasolVersionNumber("2025.1.3");
    private static final List<ExasolVersionNumber> RELEASES = List.of(RELEASE_600, RELEASE_700, RELEASE_610,
            RELEASE_601, RELEASE_600D1, RELEASE_602, RELEASE_800, RELEASE_82913, RELEASE_202513);

    @Test
    void testNotEquals() {
        for (final ExasolVersionNumber release1 : RELEASES) {
            for (final ExasolVersionNumber release2 : RELEASES) {
                if (release1 == release2) {
                    continue;
                }
                assertReleasesNotEqual(release1, release2);
            }
        }
    }

    @Test
    void testOtherClassIsNotEqual() {
        assertReleasesNotEqual(RELEASE_600, new Object());
    }

    @Test
    void testEquals() {
        assertReleasesEqual(RELEASE_600, new ExasolVersionNumber(RELEASE_600.toString()));
    }

    @Test
    void testIdentical() {
        assertReleasesEqual(RELEASE_600, RELEASE_600);
    }

    @Test
    void testSort() {
        assertThat(RELEASES.stream().sorted().collect(Collectors.toList()),
                contains(RELEASE_600, RELEASE_600D1, RELEASE_601, RELEASE_602, RELEASE_610, RELEASE_700, RELEASE_800,
                        RELEASE_82913, RELEASE_202513));
    }

    @Test
    void testToString() {
        assertThat(RELEASE_600.toString(), equalTo("6.0.0"));
    }

    @Test
    void testMajor() {
        assertThat(RELEASE_202513.getMajorVersion(), equalTo(2025));
    }

    @Test
    void testMinor() {
        assertThat(RELEASE_202513.getMinorVersion(), equalTo(1));
    }

    @Test
    void testUnsupportedReleaseTag() {
        assertThrows(IllegalStateException.class, () -> new ExasolVersionNumber("bad-format"));
    }

    void assertReleasesEqual(final Object release1, final Object release2) {
        assertAll(//
                () -> assertThat(release1.equals(release2), equalTo(true)),
                () -> assertThat(release1.hashCode(), equalTo(release2.hashCode()))//
        );
    }

    void assertReleasesNotEqual(final Object release1, final Object release2) {
        assertAll(//
                () -> assertThat(release1.equals(release2), not(equalTo(true))),
                () -> assertThat(release1.hashCode(), not(equalTo(release2.hashCode())))//
        );
    }
}
