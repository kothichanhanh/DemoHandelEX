package com.msb.epay.service.RestTemplate.impl;

import com.fasterxml.jackson.databind.*;
import com.msb.epay.service.RestTemplate.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.util.*;

@Slf4j
public class ResttemplateAPPLICATION_JSON<T> implements RestTemplateClient {
    private T data;
    public ResttemplateAPPLICATION_JSON(T data) {
        this.data = data;
    }
    @Override
    public ResponseEntity<JsonNode> callRestClient(String url) {
        log.info("URL APT VNPT {}", url);
        log.info("send {} to API {} VNPT",data,url);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<T>(data,headers);
        ResponseEntity<JsonNode> responseEntity = null;
        try{
            responseEntity = restTemplate.postForEntity(url,entity,JsonNode.class);
        } catch (Exception e){
            e.printStackTrace();
            log.info("send {} to API {} VNPT sucssec: {}",data,url,responseEntity);
        }
        return responseEntity;
    }
}
