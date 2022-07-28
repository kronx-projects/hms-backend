package ru.kronx.hmsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.entity.HotelRoom;
import ru.kronx.hmsbackend.entity.Request;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.service.TelegramMessageService;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {
    @Autowired
    TelegramMessageService telegramMessageService;
    @PostMapping("/new-request")
    public ResponseEntity addNewRequest(@RequestBody Request entity) {
        telegramMessageService.sendMessage(entity);
        return new ResponseEntity(HttpStatus.OK);
    }
}
