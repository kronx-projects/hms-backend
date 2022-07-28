package ru.kronx.hmsbackend.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kronx.hmsbackend.entity.Request;

@Service
public class TelegramMessageServiceImpl implements TelegramMessageService {

    private RestTemplate restTemplate;
    private final String TOKEN = "XXXX";
    private final String URL = "https://api.telegram.org/bot";
    private final String CHAT_ID = "418616596";
    private final String SEND = "/sendMessage";

    public TelegramMessageServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public void sendMessage(Request object) {
        HttpHeaders headers = new HttpHeaders();
        String requestBody = String.format("{\"chat_id\": \"%s\",\"text\": \"%s\"}", CHAT_ID, object.toString());
        headers.setContentType(MediaType.APPLICATION_JSON);//Content-Type
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(URL + TOKEN + SEND, HttpMethod.POST, httpEntity, String.class);
    }
}
