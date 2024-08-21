package com.rest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Constants.class.getClassLoader().getResourceAsStream("configuration/Application.properties")) {
            if (input == null) {
                throw new IllegalStateException("Sorry, unable to find configuration/Application.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static final String BASE_URL = properties.getProperty("restapi.baseurl");
    public static final String CREATE_TOKEN_ENDPOINT = "/auth";
}