package ru.kronx.hmsbackend.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingsEmptyException extends Exception {
	
	public BookingsEmptyException() {
		super("Отсутствуют актуальные брони");
	}
	
	public BookingsEmptyException(String clientId) {
		super(String.format("У клиента %d отсутствуют актуальные брони", clientId));
	}
	
	public BookingsEmptyException(LocalDate start, LocalDate end) {
		super(String.format("с %s по %s актуальные брони отсутствуют", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				, end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
	}
}
