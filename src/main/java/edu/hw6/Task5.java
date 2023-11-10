package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public final class Task5 {

    private Task5() {
    }

    private final static int SECONDS_TO_WAIT = 5;

    public static long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        String json = getStringFromURI(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"));
        String[] splitResponse = json.substring(1, json.length() - 1).split(",");
        return Arrays.stream(splitResponse)
            .mapToLong(Long::parseLong)
            .toArray();
    }

    public static String news(long id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id));

        String json = getStringFromURI(uri);
        Pattern pattern = Pattern.compile("\"title\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(json);

        return matcher.find() ? matcher.group(1) : "";
    }

    private static String getStringFromURI(URI uri) throws IOException, InterruptedException {
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
