package edu.project3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.net.http.HttpClient.newHttpClient;

public class HttpSource implements LogSource {

    private final URI uri;
    private static final int SECONDS_TO_WAIT = 5;
    private static final Logger LOGGER = LogManager.getLogger();

    public HttpSource(URI uri) {
        this.uri = uri;
    }

    @Override
    public List<String> readStringsFromSource() {
        try {
            return readStringFromHTTPClient(uri).lines().toList();
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e);
            return List.of();
        }
    }

    private static String readStringFromHTTPClient(URI uri) throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .timeout(Duration.of(SECONDS_TO_WAIT, ChronoUnit.SECONDS))
            .build();

        try (HttpClient client = newHttpClient()) {
            var response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }
}
