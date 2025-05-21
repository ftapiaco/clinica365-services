package com.clinica.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ReadJsonData {

    public static <T extends Object> T read(String id,Class<T> obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        try {
            Map<String, String> map = objectMapper.readValue(new String(Files.readAllBytes(Paths.get("src/test/resources/data.json"))), Map.class);
            return objectMapper.readValue(objectMapper.writeValueAsString(map.get(id)), obj) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
