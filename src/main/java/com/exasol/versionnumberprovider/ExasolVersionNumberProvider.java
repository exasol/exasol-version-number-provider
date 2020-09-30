package com.exasol.versionnumberprovider;

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
     * Get a list of all available releases.
     * 
     * @return list of all available releases
     */
    public List<String> getAllReleaseNumbers() {
        return this.allReleases.stream().map(ExasolVersionNumber::toString).collect(Collectors.toList());
    }
}
