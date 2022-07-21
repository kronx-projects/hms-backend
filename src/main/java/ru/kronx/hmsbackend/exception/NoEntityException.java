package ru.kronx.hmsbackend.exception;

public class NoEntityException extends Exception {
    private Long userId;

    public NoEntityException(Long id) {
        super("Cущность с id " + id + " не найдена");
    }
    public NoEntityException() {
        super("Нет записей");
    }

}
