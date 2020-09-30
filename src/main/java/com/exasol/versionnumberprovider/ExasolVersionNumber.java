package com.exasol.versionnumberprovider;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents Exasol docker-db version numbers.
 */
class ExasolVersionNumber implements Comparable<ExasolVersionNumber> {
    private static final Pattern DOCKER_IMAGE_VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(?:-d(.+))?");
    private final int major;
    private final int minor;
    private final int fix;
    private final String imageRevision;
    private final String versionNumberString;

    /**
     * Create a new instance of {@link ExasolVersionNumber}.
     *
     * @param versionNumberString version number to wrap
     */
    ExasolVersionNumber(final String versionNumberString) {
        this.versionNumberString = versionNumberString;
        final Matcher matcher = DOCKER_IMAGE_VERSION_PATTERN.matcher(versionNumberString);
        if (!matcher.matches()) {
            throw new IllegalStateException("Unsupported release tag: " + versionNumberString);
        }
        this.major = Integer.parseInt(matcher.group(1));
        this.minor = Integer.parseInt(matcher.group(2));
        this.fix = Integer.parseInt(matcher.group(3));
        this.imageRevision = matcher.group(4) == null ? "" : matcher.group(4);
    }

    @Override
    public int compareTo(final ExasolVersionNumber other) {
        if (this.major != other.major) {
            return Integer.compare(this.major, other.major);
        } else if (this.minor != other.minor) {
            return Integer.compare(this.minor, other.minor);
        } else if (this.fix != other.fix) {
            return Integer.compare(this.fix, other.fix);
        } else {
            return this.imageRevision.compareTo(other.imageRevision);
        }
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExasolVersionNumber)) {
            return false;
        }
        final ExasolVersionNumber that = (ExasolVersionNumber) other;
        return this.major == that.major && this.minor == that.minor && this.fix == that.fix
                && this.imageRevision.equals(that.imageRevision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.major, this.minor, this.fix, this.imageRevision);
    }

    @Override
    public String toString() {
        return this.versionNumberString;
    }

    /**
     * Get the MAJOR version.
     *
     * @return integer MAJOR of version string "MAJOR.MINOR.FIX(suffix)"
     */
    public int getMajorVersion() {
        return major;
    }

    /**
     * @return integer MINOR of version string "MAJOR.MINOR.FIX(suffix)"
     */
    public int getMinorVersion() {
        return minor;
    }
}
