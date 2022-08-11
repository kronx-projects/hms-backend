package ru.kronx.hmsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.dto.impl.RequestBooking;
import ru.kronx.hmsbackend.service.TelegramMessageService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

    @Autowired
    TelegramMessageService telegramMessageService;

    @PostMapping("/new-request")
    public ResponseEntity addNewRequest(@Valid @RequestBody RequestBooking requestBooking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else {
            telegramMessageService.sendMessage(requestBooking);
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
