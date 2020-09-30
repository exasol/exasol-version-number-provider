package com.exasol.versionnumberprovider;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.json.JsonArray;

/**
 * This class gives you access to the available Exasol docker-db version numbers.
 */
class ExasolVersionNumberProvider {
    private static final Pattern DOCKER_IMAGE_VERSION_PATTERN = Pattern
            .compile("(\\d+)(?:\\.(\\d+))?(?:\\.(\\d+))?(?:-d(\\d+))?");
    private final List<ExasolVersionNumber> allReleases;

    /**
     * Create a new instance of {@link ExasolVersionNumberProvider}.
     *
     * <p>
     * Package-private since it is built by the {@link ExasolVersionNumberProviderFactory}
     * </p>
     *
     * @param dockerHubTagDescription tag description from docker-hub API
     */
    ExasolVersionNumberProvider(final JsonArray dockerHubTagDescription) {
        this.allReleases = dockerHubTagDescription.stream().map(release -> release.asJsonObject().getString("name"))
                .filter(tag -> !tag.startsWith("latest")).map(ExasolVersionNumber::new).sorted()
                .collect(Collectors.toList());
    }

    /**
     * Get the latest release of the docker-db.
     *
     * <p>
     * Instead of using latest, this ensures that your computer will actively fetch the latest version and not use the
     * latest installed one.
     * </p>
     */
    public String getLatestReleaseNumber() {
        return this.allReleases.get(this.allReleases.size() - 1).toString();
    }

    /**
     * Get the latest release in the given major branch.
     *
     * @param majorReleaseNumber Integer major release number, eg. (6) or (7)
     * @return Latest version with that major
     * @throws java.util.NoSuchElementException if the major does not match anything
     */
    public ExasolVersionNumber getLatestReleaseForMajor(int majorReleaseNumber) {
        return this.allReleases.stream().filter(version -> version.getMajorVersion() == majorReleaseNumber)
                .max(Comparator.naturalOrder()).orElseThrow();
    }

    /**
     * Get the latest release in the given major.minor branch
     *
     * @param majorReleaseNumber Integer major release number, eg. (6) or (7)
     * @param minorReleaseNumber Integer minor release number, eg (0), (1), (2), ...
     * @return Latest version with that major.minor
     * @throws java.util.NoSuchElementException if the major.minor does not match anything
     */
    public ExasolVersionNumber getLatestReleaseForMinor(int majorReleaseNumber, int minorReleaseNumber) {
        return this.allReleases.stream()
                .filter(version -> version.getMajorVersion() == majorReleaseNumber
                        && version.getMinorVersion() == minorReleaseNumber)
                .max(Comparator.naturalOrder()).orElseThrow();
    }

    /**
     * Get a list of all available releases.
     *
     * @return list of all available releases
     */
    public List<String> getAllReleaseNumbers() {
        return this.allReleases.stream().map(ExasolVersionNumber::toString).collect(Collectors.toList());
    }
}
