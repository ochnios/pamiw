package pl.ochnios.todofrontend.models.services.shared;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientUtil {
    private static final int HTTP_OK = 200;
    private static final int HTTP_CREATED = 201;
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static String makeGetRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        return sendRequest(request, "GET", HTTP_OK);
    }

    public static String makePostRequest(URI uri, String body) throws IOException, InterruptedException {
        System.out.println(body);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        return sendRequest(request, "POST", HTTP_CREATED);
    }

    public static String makePatchRequest(URI uri, String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(body)).build();
        return sendRequest(request, "PATCH", HTTP_OK);
    }

    public static String makeDeleteRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri).DELETE().build();
        return sendRequest(request, "DELETE", HTTP_OK);
    }

    private static String sendRequest(HttpRequest request, String httpMethod, int successCode) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != successCode) {
            throw new IOException(httpMethod + ": Invalid HTTP response code = " + response.statusCode());
        }

        return response.body();
    }
}

