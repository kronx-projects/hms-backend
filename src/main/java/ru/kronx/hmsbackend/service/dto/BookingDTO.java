package ru.kronx.hmsbackend.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class BookingDTO {
	private final String id;
	private final String start;
	private final String end;
	private final String name;
	private final String description;
	
	public BookingDTO(Long id, LocalDateTime start, LocalDateTime end, String name, String surname, String description) {
		this.id = id.toString();
		this.start = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.end = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.name = surname + " " + name;
		this.description = description;
	}
}
