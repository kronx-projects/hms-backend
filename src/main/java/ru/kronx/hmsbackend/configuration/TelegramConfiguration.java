package ru.kronx.hmsbackend.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "telegram.api")
@Getter
@Setter
public class TelegramConfiguration {

    private String token;
    private String chat;
}
