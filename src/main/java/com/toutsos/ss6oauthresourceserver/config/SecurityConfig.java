package com.toutsos.ss6oauthresourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.management.MXBean;

@Configuration
public class SecurityConfig {

    @Value("${jwksUri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(
                /**
                 * at this url the resource server will get the public keys from auth sever to check token signature
                 */
                r -> r.jwt().jwkSetUri(jwksUri)
        );

        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();

    }

}
