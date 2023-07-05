package com.msb.epay.service.impl;

import com.fasterxml.jackson.databind.*;
import com.msb.epay.Dto.RequestDto.*;
import com.msb.epay.Dto.ResponeDataDTO.*;
import com.msb.epay.Dto.*;
import com.msb.epay.service.*;
import com.msb.epay.service.RestTemplate.*;
import com.msb.epay.service.RestTemplate.impl.*;
import com.msb.epay.common.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import javax.validation.*;

@Slf4j
@Service
public class EpayServiceImpl implements EpayService {
    @Value("${vnpt.url}")
    String url;
    @Value("${vnpt.merId}")
    String merId;
    @Value("${vnpt.enCodeKey}")
    String enCodeKey;
    @Value("${vnpt.getinfourl}")
    String getinfourl;
    @Value("${vnpt.key}")
    String key;
    @Value("${vnpt.changetoinstallmenturl}")
    String changetoinstallmenturl;
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @SneakyThrows
    @Override
    public ResponeDto getInfoInstallmant(String amount) {
        if(!NumberUtils.isCreatable(amount)){
            throw new ValidationException("amount is invalid, " + amount + " not is number");
        }
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("merId",merId);
        String merTrxId = merId + DateTimeUtil.getTodayyyyMMdd();
        map.add("merTrxId",merTrxId);
        map.add("amount",amount);
        String timeStamp = DateTimeUtil.getTodayyyyMMddHHmmss();
        map.add("timeStamp",timeStamp);
        String merchantToken = Encode.enscriptSHA256(timeStamp + merTrxId + merId + amount + enCodeKey);
        map.add("merchantToken",merchantToken);
        RestTemplateClient restTemplateClient = new RestTemplateClientAPPLICATION_FORM_URLENCODED();
        restTemplateClient.initAPPLICATION_FORM_URLENCODED(map);
        ResponseEntity<JsonNode> responseEntity = restTemplateClient.callRestClient(getinfourl);
        JsonNode boby = responseEntity.getBody();
        InstallmentDto installmentDto = objectMapper.convertValue(boby,InstallmentDto.class);
        String dataBank = DesedeCrypter.decrypt(key,installmentDto.getData());
        Object o = objectMapper.readValue(dataBank,Object.class);
        ResponeDto<InstallmentDto> responeDto = new ResponeDto<>();
        installmentDto.setInstallmentInfo(o);
        installmentDto.setData(null);
        responeDto.setMesg("OK!");
        responeDto.setCode("00");
        responeDto.setData(installmentDto);
        return responeDto;
    }

    @Override
    public ResponeDto changeToInstallment(ChageToIstallmentDto chageToIstallmentDto) {
        RestTemplateClient client = new ResttemplateAPPLICATION_JSON<ChageToIstallmentDto>(chageToIstallmentDto);

        String merchantToken = chageToIstallmentDto.merchantTokenEncoding(enCodeKey);
        chageToIstallmentDto.setMerchantToken(merchantToken);

        ResponseEntity<JsonNode> responseEntity = client.callRestClient(changetoinstallmenturl);
        ChangeToInstallmentRpDto changeToInstallmentRpDto = objectMapper.convertValue(responseEntity.getBody(),ChangeToInstallmentRpDto.class);
        ResponeDto<ChangeToInstallmentRpDto> responeDto = new ResponeDto<>();
        responeDto.setData(changeToInstallmentRpDto);
        responeDto.setCode("00");
        responeDto.setMesg("OK!");
        return responeDto;
    }
}
