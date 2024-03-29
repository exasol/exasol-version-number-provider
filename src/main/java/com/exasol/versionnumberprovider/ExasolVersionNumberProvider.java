package com.exasol.versionnumberprovider;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.exasol.versionnumberprovider.dockerhub.Tag;

/**
 * This class gives you access to the available Exasol docker-db version numbers.
 */
public class ExasolVersionNumberProvider {
    private final List<ExasolVersionNumber> allReleases;
    private static final Pattern PRE_RELEASE_PATTERN = Pattern.compile(".{0,40}(?:prerelease|alpha|beta|rc).{0,40}");

    /**
     * Create a new instance of {@link ExasolVersionNumberProvider}.
     *
     * <p>
     * Package-private since it is built by the {@link ExasolVersionNumberProviderFactory}
     * </p>
     *
     * @param allTags all tag descriptions from docker-hub API
     */
    ExasolVersionNumberProvider(final List<Tag> allTags) {
        this.allReleases = extractVersions(allTags);
    }

    private static List<ExasolVersionNumber> extractVersions(final List<Tag> allTags) {
        return allTags.stream() //
                .map(Tag::getName) //
                .filter(ExasolVersionNumberProvider::isIncludedTag) //
                .map(ExasolVersionNumber::new) //
                .sorted() //
                .collect(Collectors.toList());
    }

    private static boolean isIncludedTag(final String tag) {
        return !(tag.startsWith("latest") || PRE_RELEASE_PATTERN.matcher(tag).matches());
    }

    /**
     * Get the latest release of the docker-db.
     *
     * <p>
     * Instead of using latest, this ensures that your computer will actively fetch the latest version and not use the
     * latest installed one.
     * </p>
     * 
     * @return latest release number
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
    public String getLatestReleaseForMajor(final int majorReleaseNumber) {
        return this.allReleases.stream().filter(version -> version.getMajorVersion() == majorReleaseNumber)
                .max(Comparator.naturalOrder()).orElseThrow().toString();
    }

    /**
     * Get the latest release in the given major.minor branch.
     *
     * @param majorReleaseNumber Integer major release number, eg. (6) or (7)
     * @param minorReleaseNumber Integer minor release number, eg (0), (1), (2), ...
     * @return Latest version with that major.minor
     * @throws java.util.NoSuchElementException if the major.minor does not match anything
     */
    public String getLatestReleaseForMinor(final int majorReleaseNumber, final int minorReleaseNumber) {
        return this.allReleases.stream()
                .filter(version -> version.getMajorVersion() == majorReleaseNumber
                        && version.getMinorVersion() == minorReleaseNumber)
                .max(Comparator.naturalOrder()).orElseThrow().toString();
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
