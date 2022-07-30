package ru.kronx.hmsbackend.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingsEmptyException extends Exception {
	
	public BookingsEmptyException() {
		super("Отсутствуют актуальные брони");
	}
	
	public BookingsEmptyException(Long clientId) {
		super(String.format("У клиента c id %d отсутствуют актуальные брони", clientId));
	}
	
	public BookingsEmptyException(LocalDateTime start, LocalDateTime end) {
		super(String.format("с %s по %s актуальные брони отсутствуют", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
				, end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
	}
}
