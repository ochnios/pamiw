package pl.ochnios.pamiw.models.services;

import pl.ochnios.pamiw.core.Consts;
import pl.ochnios.pamiw.models.auxiliary.location.Location;
import pl.ochnios.pamiw.models.services.shared.HttpClientUtil;
import pl.ochnios.pamiw.models.services.shared.ObjectMapperUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LocationService {
    private static final String LOCATIONS_EP = Consts.BASE_URL + "locations/v1/cities/autocomplete";
    private Location[] foundLocations;

    public List<String> searchLocations(String searchPhrase) throws Exception {
        URI searchURI = createSearchLocationURI(searchPhrase);
        String locationsJson = HttpClientUtil.makeHttpRequest(searchURI);

        foundLocations = ObjectMapperUtil.getObjectMapper().readValue(locationsJson, Location[].class);

        return getCities(foundLocations);
    }

    public String getCityKeyForIndex(int index) {
        if (index < 0 || index > foundLocations.length) {
            throw new IllegalArgumentException("Provided index is incorrect!");
        } else {
            return foundLocations[index].getKey();
        }
    }

    private URI createSearchLocationURI(String searchPhrase) throws URISyntaxException {
        String encodedSearchPhrase = URLEncoder.encode(searchPhrase, StandardCharsets.UTF_8);
        String uri = LOCATIONS_EP + "?q=" + encodedSearchPhrase
                + "&language=" + Consts.LANGUAGE
                + "&apikey=" + Consts.APIKEY;

        return new URI(uri);
    }

    private List<String> getCities(Location[] locations) {
        int locationsCount = locations.length;
        if (locationsCount > 0) {
            ArrayList<String> cities = new ArrayList<String>(locationsCount);
            for (Location location : locations) {
                cities.add(
                        location.getLocalizedName()
                        + " (" + location.getAdministrativeArea().getLocalizedName()
                        + ", " + location.getCountry().getLocalizedName() + ")"
                );
            }
            return cities;
        } else {
            return List.of(Consts.NOT_FOUND_TEXT);
        }
    }

}
