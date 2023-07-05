package com.msb.epay.service.RestTemplate.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.msb.epay.service.RestTemplate.RestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RestTemplateClientAPPLICATION_FORM_URLENCODED implements RestTemplateClient {
    private MultiValueMap<String, String> map;
    @Override
    public void initAPPLICATION_FORM_URLENCODED(MultiValueMap<String, String> map) {
        this.map = map;
    }
    @Override
    public ResponseEntity<JsonNode> callRestClient(String url) {
        log.info("URL APT VNPT {}", url);
        log.info("send {} to API {} VNPT",map,url);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestBodyFormUrlEncoded =
                new HttpEntity<>(map, headers);
        ResponseEntity<JsonNode> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(url, requestBodyFormUrlEncoded, JsonNode.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("send {} to API {} VNPT error: {}",map,url,e.getMessage());
        }
        log.info("send {} to API {} VNPT sucssec: {}",map,url,responseEntity);
        return responseEntity;
    }
}
