package ru.kronx.hmsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.dto.impl.RequestBooking;
import ru.kronx.hmsbackend.service.TelegramMessageService;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {
    @Autowired
    TelegramMessageService telegramMessageService;
    @PostMapping("/new-request")
    public ResponseEntity addNewRequest(@RequestBody RequestBooking requestBooking) {
        telegramMessageService.sendMessage(requestBooking);
        return new ResponseEntity(HttpStatus.OK);
    }
}
