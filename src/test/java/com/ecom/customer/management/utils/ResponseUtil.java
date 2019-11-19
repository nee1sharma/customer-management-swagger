package com.ecom.customer.management.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ResponseUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static <T> T readJson(Class<T> clazz, String tag) throws IOException {
        String resourcePath = String.format("/%1s/%2s.%3s.json", "json", clazz.getSimpleName(), tag);
        return MAPPER.readValue(ResponseUtil.class.getResourceAsStream(resourcePath), clazz);
    }

    public static <T> T readValue(String value, Class<T> clazz) throws JsonParseException, JsonMappingException,
            IOException {
        return MAPPER.readValue(value, clazz);
    }

    public static String readValueAsString(Object value) throws JsonProcessingException {
        return MAPPER.writeValueAsString(value);
    }
}
