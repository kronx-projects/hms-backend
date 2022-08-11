package ru.kronx.hmsbackend.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "allowed")
@Getter
@Setter
public class AllowedHostConfiguration {
    private String origin;
}
