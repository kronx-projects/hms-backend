package ru.kronx.hmsbackend.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kronx.hmsbackend.dto.TelegramMessageDTO;
import ru.kronx.hmsbackend.service.TelegramMessageService;

@Service
public class TelegramMessageServiceImpl implements TelegramMessageService {

    private RestTemplate restTemplate;
    @Value("5528005416:AAFGhsQNkGSZ-L8OEwxT-VyL1kadM-ixkYc")
    private String token;
    @Value("418616596")
    private String chatId;
    private String url;


    public TelegramMessageServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public void sendMessage(TelegramMessageDTO telegramMessageDTO) {
        url = "https://api.telegram.org/bot" + token + "/sendMessage";
        HttpHeaders headers = new HttpHeaders();
        String text = telegramMessageDTO.getTelegramMessage();
        String requestBody = String.format(
                "{\"chat_id\": \"%s\",\"parse_mode\": \"html\",\"text\": \"%s\"}",
                chatId, text);
        headers.setContentType(MediaType.APPLICATION_JSON);//Content-Type
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(url, HttpMethod.POST, httpEntity, String.class);
    }
}
