package org.projectzion.game.services.overpass.turbo;

import org.projectzion.game.configs.OverpassTurboConfig;
import org.projectzion.game.utils.Gps;
import org.projectzion.game.tos.OverpassTurboResult;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Service
public class OverpassTurboService {

    @Autowired
    OverpassTurboConfig overpassTurboConfig;

    //    [bbox:south,west,north,east]
    //    // A bbox framing the German city of Bonn
    //    [bbox:50.6,7.0,50.8,7.3]
    public static final String BOUNDING_BOX_PLACEHOLDER =  "#BOUNDING_BOX_PLACEHOLDER#";
    public static final String BOUNDING_BOX =  "[bbox:#SOUTH#,#WEST#,#NORTH#,#EAST#]";
    public static final String BOUNDING_BOX_SOUTH = "#SOUTH#";
    public static final String BOUNDING_BOX_WEST = "#WEST#";
    public static final String BOUNDING_BOX_NORTH = "#NORTH#";
    public static final String BOUNDING_BOX_EAST = "#EAST#";

    public static final String PARAM_RADIUS = "\\_\\_RADIUS\\_\\_";
    public static final String PARAM_LATITUDE = "\\_\\_LATITUDE\\_\\_";
    public static final String PARAM_LONGITUDE = "\\_\\_LONGITUDE\\_\\_";

    public static final String query_drinkingwater = "[out:json]; \n" +
            BOUNDING_BOX_PLACEHOLDER + ";\n" +
            "node\n" +
            "  [amenity=drinking_water]\n" +
            "  (around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            "out;";

    public static final String query_postbox = "[out:json];\n" +
            BOUNDING_BOX_PLACEHOLDER + ";\n" +
            "node\n" +
            "  [amenity=post_box]\n" +
            "  (around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            "out;";

//    public static final String query_drinkingwater_and_postbox = "[out:json];\n" +
//            BOUNDING_BOX_PLACEHOLDER + ";\n" +
//            "(\n" +
//            "  node[amenity=post_box](around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
//            "  node[amenity=drinking_water](around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
//            ");\n" +
//            "out;";

    private String prepareQuery(String query, Gps gps, BigDecimal radius){
        String preparedQuery = query;
        preparedQuery = preparedQuery.replaceAll(PARAM_RADIUS, radius.toString());
        preparedQuery = preparedQuery.replaceAll(PARAM_LATITUDE, String.valueOf(gps.getLat()));
        preparedQuery = preparedQuery.replaceAll(PARAM_LONGITUDE, String.valueOf(gps.getLon()));
//        return URLEncoder.encode(preparedQuery);
        return preparedQuery;
    }

    private String prepareBbox(double south,double west,double north,double east){
        return BOUNDING_BOX
                .replaceAll(BOUNDING_BOX_SOUTH, String.valueOf(south))
                .replaceAll(BOUNDING_BOX_WEST, String.valueOf(west))
                .replaceAll(BOUNDING_BOX_NORTH, String.valueOf(north))
                .replaceAll(BOUNDING_BOX_EAST, String.valueOf(east));
    }

    public static final String query_drinkingwater_and_postbox = "[out:json];\n" +
            BOUNDING_BOX_PLACEHOLDER + ";\n" +
            "(\n" +
            "  node[amenity=post_box](around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            "  node[amenity=drinking_water](around:__RADIUS__, __LATITUDE__, __LONGITUDE__);\n" +
            ");\n" +
            "out;";

    public static final String CRITERIA_PLACEHOLDER = "#CRITERIA_PLACEHOLDER#";
    public static final String QUERY_HOLLOW = "[out:json];\n" +
            BOUNDING_BOX_PLACEHOLDER + ";\n" +
            "(\n" +
            CRITERIA_PLACEHOLDER  + ";\n" +
            ");\n" +
            "out;";

    public OverpassTurboResult getNodesFromBox(double east, double west, double south, double north, List<NodeCriteria> criterias) throws IOException {
        OverpassTurboResult ret = null;
        String query = QUERY_HOLLOW;
        String bbox = prepareBbox(south,west,north,east);
        StringBuffer crits = new StringBuffer();

        criterias.forEach(critera -> {
            crits.append(critera.getOverpassTurboNodeType().getOsmName());
            if(critera.getFilter() != null)
            {
                critera.getFilter().forEach((key, value) -> {
                    crits.append("[").append(key.filter).append("=\"").append(value.value).append("=\"").append("]");
                });
                crits.append("\n");
            }
        });
        query = query.replaceAll(BOUNDING_BOX_PLACEHOLDER, bbox).replaceAll(CRITERIA_PLACEHOLDER, crits.toString());
        ret = executeQuery(query);

        return ret;
    }


    public OverpassTurboResult executeQuery(String plainTextQuery){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("data", plainTextQuery);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OverpassTurboResult> response = restTemplate.postForEntity( overpassTurboConfig.getInterpreterUrl(), request , OverpassTurboResult.class );
        return response.getBody();
    }

    public OverpassTurboResult getNodesByGps(String query, Gps gps, BigDecimal radius) throws IOException {
        String preparedQuery = prepareQuery(query, gps, radius);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("data", preparedQuery);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<OverpassTurboResult> response = restTemplate.postForEntity( overpassTurboConfig.getInterpreterUrl(), request , OverpassTurboResult.class );
        return response.getBody();
    }
}
