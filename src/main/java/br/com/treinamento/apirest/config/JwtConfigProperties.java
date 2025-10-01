package br.com.treinamento.apirest.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "br.com.treinamento.apirest.jwt")
public class JwtConfigProperties {

    private String secret;
    private Duration expiration;
    
}