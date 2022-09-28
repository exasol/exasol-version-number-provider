package com.exasol.versionnumberprovider;

import java.util.List;

import com.exasol.versionnumberprovider.dockerhub.DockerhubClient;
import com.exasol.versionnumberprovider.dockerhub.Tag;

/**
 * Factory for {@link ExasolVersionNumberProvider}.
 * <p>
 * This factory fetches the list of release tags from the internet.
 * </p>
 */
public class ExasolVersionNumberProviderFactory {
    /**
     * Build an {@link ExasolVersionNumberProvider} using a tag list from docker hub.
     * 
     * @return built {@link ExasolVersionNumberProvider}.
     */
    public ExasolVersionNumberProvider buildExasolVersionNumberProvider() {
        final List<Tag> allTags = new DockerhubClient().getAllTags();
        return new ExasolVersionNumberProvider(allTags);
    }
}
