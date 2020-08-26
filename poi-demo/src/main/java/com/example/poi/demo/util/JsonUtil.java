package com.example.poi.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JsonUtil {
    public <T> T parseFromJSONFile(TypeReference<T> tClass, String filePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        File jsonFile = new File(Objects.requireNonNull(classLoader.getResource(filePath)).getFile());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T object = null;
        try {
            object = objectMapper.readValue(jsonFile, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
