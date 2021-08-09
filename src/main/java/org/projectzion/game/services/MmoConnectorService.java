package org.projectzion.game.services;

import org.projectzion.game.configs.security.MmoConnectorConfig;
import org.projectzion.game.persitence.entities.Node;
import org.projectzion.game.persitence.entities.TargetSystem;
import org.projectzion.game.scoped.request.RequestScoped;
import org.projectzion.game.tos.OverpassTurboResult;
import org.projectzion.game.tos.mmoconnector.pick.PickRequest;
import org.projectzion.game.tos.mmoconnector.pick.PickResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class MmoConnectorService {

    @Autowired
    MmoConnectorConfig mmoConnectorConfig;

    @Autowired
    RequestScoped requestScoped;

    protected HttpHeaders getHttpHeaders(){
        HttpHeaders header = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        List<Charset> sets = new ArrayList<>();
        sets.add(utf8);
        header.setAcceptCharset(sets);
        header.setContentType(MediaType.APPLICATION_JSON);
        return header;
    }

    protected RestTemplate getRestTemplate(){
        //TODO REMOVE ON OAUTH2
        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication(requestScoped.currentUserPrincipal().getUser().getEmail(),requestScoped.currentUserPrincipal().getUser().getPasswordClear()).build();
//        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    public boolean pick(Node node, TargetSystem targetSystem){
        PickRequest pickRequest = new PickRequest();
        pickRequest.setTargetSystem(targetSystem.getId());
        pickRequest.setNodeType(node.getNodeType().getId());

        HttpEntity<PickRequest> entity = new HttpEntity<PickRequest>(pickRequest, getHttpHeaders());
        boolean hasBody = entity.hasBody();
        if(hasBody)
        {
        }
        ResponseEntity<String> response = getRestTemplate().postForEntity(URI.create(mmoConnectorConfig.getUrl()), entity, String.class);

        return response.getStatusCode() == HttpStatus.OK;
    }
}
