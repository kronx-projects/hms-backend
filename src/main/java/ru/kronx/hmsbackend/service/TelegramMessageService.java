package ru.kronx.hmsbackend.service;

import ru.kronx.hmsbackend.dto.TelegramMessageDTO;

public interface TelegramMessageService {

    public void sendMessage(TelegramMessageDTO telegramMessageDTO);

}
