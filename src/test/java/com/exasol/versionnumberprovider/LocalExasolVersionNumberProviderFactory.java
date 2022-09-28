package com.exasol.versionnumberprovider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.exasol.versionnumberprovider.dockerhub.Deserializer;
import com.exasol.versionnumberprovider.dockerhub.TagListPage;

public class LocalExasolVersionNumberProviderFactory {

    public ExasolVersionNumberProvider getLocalDockerHubResponseDump(final Path path) throws IOException {
        final TagListPage tagList = readTagList(path);
        return new ExasolVersionNumberProvider(tagList.getTags());
    }

    private TagListPage readTagList(final Path path) throws IOException {
        return new Deserializer().deserialize(Files.readString(path));
    }
}
