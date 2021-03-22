package com.example.restservice.services;

import com.example.restservice.utils.Gps;
import com.example.restservice.utils.Node;
import com.example.restservice.tos.OverpassTurboResult;
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
import java.util.ArrayList;
import java.util.List;

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

    public List<Node> getNodesByGps(String query, Gps gps, BigDecimal radius) throws IOException {
        List<Node> ret = new ArrayList<>();
        String preparedQuery = prepareQuery(query, gps, radius);

//        URL url = new URL(HTTPS_OVERPASS_API_DE_API_INTERPRETER);
//        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty();

//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl
//                = "http://localhost:8080/spring-rest/foos";
//        HashMap<String, Object > map = new HashMap<>();
//        map.put("form-data", )
//        String response = restTemplate.getForObject(HTTPS_OVERPASS_API_DE_API_INTERPRETER, String.class, map);
//        System.out.println(response);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("data", preparedQuery);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OverpassTurboResult> response = restTemplate.postForEntity( HTTPS_OVERPASS_API_DE_API_INTERPRETER, request , OverpassTurboResult.class );

        System.out.println(response.getBody());

        return ret;
    }
}
