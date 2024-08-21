// src/test/java/com/rest/config/AuthConfig.java
package com.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Value("${auth.username}")
    private String username;

    @Value("${auth.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}