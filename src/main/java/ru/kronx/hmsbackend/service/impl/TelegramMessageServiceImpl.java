package ru.kronx.hmsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kronx.hmsbackend.dto.TelegramMessageDTO;
import ru.kronx.hmsbackend.service.TelegramMessageService;

@Service
public class TelegramMessageServiceImpl implements TelegramMessageService {

    private RestTemplate restTemplate;
    private final String TOKEN = "XXXX";
    private final String URL = "https://api.telegram.org/bot" + TOKEN + "/sendMessage";
    private final String CHAT_ID = "XXXX";

    public TelegramMessageServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public void sendMessage(TelegramMessageDTO telegramMessageDTO) {
        HttpHeaders headers = new HttpHeaders();
        String text = telegramMessageDTO.getTelegramMessage();
        String requestBody = String.format(
                "{\"chat_id\": \"%s\",\"parse_mode\": \"html\",\"text\": \"%s\"}",
                CHAT_ID, text);
        headers.setContentType(MediaType.APPLICATION_JSON);//Content-Type
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, String.class);
    }
}
