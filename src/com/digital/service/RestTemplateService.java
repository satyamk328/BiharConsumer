package com.digital.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class RestTemplateService {

    @Autowired
    private RestTemplateLoggingInterceptor restTemplateLoggingInterceptor;

    @Autowired
    private RestTemplateCustomErrorHandler errorHandler;

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate rest = new RestTemplate();
        // Set up a buffering request factory, so response body is always
        // buffered
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(
                requestFactory);
        requestFactory.setOutputStreaming(false);
        rest.setRequestFactory(bufferingClientHttpRequestFactory);

        // Add the intercepter that will handle logging
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(restTemplateLoggingInterceptor);
        rest.setInterceptors(interceptors);
        rest.setErrorHandler(errorHandler);

        return rest;
    }

    public MappingJackson2HttpMessageConverter getMappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }
}
