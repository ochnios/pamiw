package pl.ochnios.pamiw.services.shared;

import pl.ochnios.pamiw.core.Consts;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientUtil {
    private static final int HTTP_OK = 200;
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static String makeHttpRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        int responseCode = response.statusCode();
        if (responseCode != HTTP_OK) {
            throw new IOException("Invalid HTTP response code: " + responseCode);
        }
        return response.body();
    }
}
