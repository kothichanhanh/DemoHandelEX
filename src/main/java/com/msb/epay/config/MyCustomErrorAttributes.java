package com.msb.epay.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyCustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes =
                super.getErrorAttributes(webRequest, options);
        Map<String, Object> r = new HashMap<>();
        r.put("code",errorAttributes.get("status"));
        r.put("mesg",errorAttributes.get("error"));
        r.put("data",errorAttributes.get("path"));
        return r;
    }
}
