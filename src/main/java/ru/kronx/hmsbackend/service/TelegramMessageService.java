package ru.kronx.hmsbackend.service;

import ru.kronx.hmsbackend.entity.Request;

public interface TelegramMessageService {

    public void sendMessage(Request object);

}
