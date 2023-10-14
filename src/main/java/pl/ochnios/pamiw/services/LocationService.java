package pl.ochnios.pamiw.services;

import com.fasterxml.jackson.databind.ObjectReader;
import pl.ochnios.pamiw.Consts;
import pl.ochnios.pamiw.models.location.Location;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.ochnios.pamiw.services.shared.HttpClientUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class LocationService {
    private final ObjectMapper om;
    private Location[] foundLocations;
    private String[] foundCities;

    public LocationService() {
        om = new ObjectMapper();
    }

    public String[] searchLocations(String searchPhrase) throws Exception {
        URI searchURI = createSearchLocationURI(searchPhrase);
        String locationsJson = HttpClientUtil.makeHttpRequest(searchURI);

        foundLocations = om.readValue(locationsJson, Location[].class);
        foundCities = getCities(foundLocations);

        return foundCities;
    }

    private URI createSearchLocationURI(String searchPhrase) throws URISyntaxException {
        String encodedSearchPhrase = URLEncoder.encode(searchPhrase, StandardCharsets.UTF_8);
        String uri = Consts.LOCATIONS_EP + "?q=" + encodedSearchPhrase +
                "&language=" + Consts.LANGUAGE +
                "&apikey=" + Consts.APIKEY;

        return new URI(uri);
    }

    private String[] getCities(Location[] locations) {
        int locationsCount = locations.length;
        if (locationsCount > 0) {
            String[] cities = new String[locationsCount];
            for (int i = 0; i < locationsCount; i++) {
                cities[i] = locations[i].localizedName
                        + " (" + locations[i].administrativeArea.localizedName
                        + ", " + locations[i].country.localizedName + ")";
            }
            return cities;
        } else {
            return new String[]{"not found"};
        }
    }

}
