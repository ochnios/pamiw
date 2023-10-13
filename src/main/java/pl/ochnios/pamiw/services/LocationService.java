package pl.ochnios.pamiw.services;

import pl.ochnios.pamiw.Consts;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class LocationService {

    private final HttpClient httpClient;

    public LocationService() {
        httpClient = HttpClient.newHttpClient();
    }

    public String searchLocations(String searchPhrase) throws Exception {
        URI searchURI = createSearchLocationURI(searchPhrase);
        HttpRequest searchRequest = HttpRequest.newBuilder()
                .uri(searchURI)
                .GET()
                .build();
        HttpResponse<String> searchResponse = httpClient
                .send(searchRequest, HttpResponse.BodyHandlers.ofString());

        return searchResponse.body();
    }

    private URI createSearchLocationURI(String searchPhrase) throws URISyntaxException {
        String encodedSearchPhrase = URLEncoder.encode(searchPhrase, StandardCharsets.UTF_8);
        String uri = Consts.LOCATIONS_EP + "?q=" + encodedSearchPhrase +
                "&language=" + Consts.LANGUAGE +
                "&apikey=" + Consts.APIKEY;

        return new URI(uri);
    }
}
