package org.projectzion.game.services;

import org.projectzion.game.utils.Gps;
import org.projectzion.game.tos.OverpassTurboResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class OverpassTurboService {

    public static final String PARAM_RADIUS = "\\_\\_RADIUS\\_\\_";
    public static final String PARAM_LATITUDE = "\\_\\_LATITUDE\\_\\_";
    public static final String PARAM_LONGITUDE = "\\_\\_LONGITUDE\\_\\_";
    public static final String HTTPS_OVERPASS_API_DE_API_INTERPRETER = "https://overpass-api.de/api/interpreter";

    public static final String query_drinkingwater = "[out:json]; \n" +
            "node\n" +
            "  [amenity=drinking_water]\n" +
            "  (around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            "out;";

    public static final String query_postbox = "[out:json]; \n" +
            "node\n" +
            "  [amenity=post_box]\n" +
            "  (around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            "out;";


    public static final String query_drinkingwater_and_postbox = "[out:json]; \n" +
            "(\n" +
            "  node[amenity=post_box](around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            "  node[amenity=drinking_water](around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            ");\n" +
            "out;";

    private String prepareQuery(String query, Gps gps, BigDecimal radius){
        String preparedQuery = query;
        preparedQuery = preparedQuery.replaceAll(PARAM_RADIUS, radius.toString());
        preparedQuery = preparedQuery.replaceAll(PARAM_LATITUDE, gps.getLat().toString());
        preparedQuery = preparedQuery.replaceAll(PARAM_LONGITUDE, gps.getLon().toString());
//        return URLEncoder.encode(preparedQuery);
        return preparedQuery;
    }

    public OverpassTurboResult getNodesByGps(String query, Gps gps, BigDecimal radius) throws IOException {
        String preparedQuery = prepareQuery(query, gps, radius);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("data", preparedQuery);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OverpassTurboResult> response = restTemplate.postForEntity( HTTPS_OVERPASS_API_DE_API_INTERPRETER, request , OverpassTurboResult.class );
        return response.getBody();
    }
}
