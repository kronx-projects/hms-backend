package ru.kronx.hmsbackend.exception;

public class EmptyRequeredFieldException extends Exception {

    public EmptyRequeredFieldException(String message) {
        super(message);
    }

    public EmptyRequeredFieldException() {
        super();
    }

}
