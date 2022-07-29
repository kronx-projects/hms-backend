package ru.kronx.hmsbackend.service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Getter;

@Getter
public class BookingDTO {
	private final String id;
	private final String start;
	private final String end;
	private final String name;
	private final String description;
	
	public BookingDTO(Long id, LocalDate start, LocalDate end, String name, String surname, String description) {
		this.id = id.toString();
		this.start = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.end = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.name = surname + " " + name;
		this.description = description;
	}
}
