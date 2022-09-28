package com.exasol.versionnumberprovider.dockerhub;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;

import com.exasol.errorreporting.ExaError;

class SimpleHttpClient {

    private final HttpClient client;

    SimpleHttpClient() {
        client = HttpClient.newBuilder() //
                .followRedirects(HttpClient.Redirect.ALWAYS) //
                .build();
    }

    String getUrl(final URI url) {
        final HttpRequest request = HttpRequest.newBuilder(url).GET().build();
        try {
            final HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (statusFailed(response.statusCode())) {
                throw new IllegalStateException(ExaError.messageBuilder("F-EVNP-3")
                        .message("Request to URL {{url}} failed with status {{status}}", url, response.statusCode())
                        .toString());
            }
            return response.body();
        } catch (final IOException exception) {
            throw new UncheckedIOException(
                    ExaError.messageBuilder("F-EVNP-4").message("Failed to request URL {{url}}", url).toString(),
                    exception);
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(ExaError.messageBuilder("F-EVNP-5")
                    .message("Interrupted while requesting URL {{url}}", url).toString(), exception);
        }
    }

    private boolean statusFailed(final int status) {
        return status / 100 != 2;
    }
}
