package com.msb.epay.service.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface RestTemplateClient {
    ResponseEntity<JsonNode> callRestClient(String url);
    default void initAPPLICATION_FORM_URLENCODED(MultiValueMap<String, String> map){
        return;
    }
}
