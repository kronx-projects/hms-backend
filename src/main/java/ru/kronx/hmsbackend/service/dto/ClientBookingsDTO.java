package ru.kronx.hmsbackend.service.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ClientBookingsDTO {
	private final String name;
	private final String backgroundColor;
	private final List<BookingDTO> list;
	
	public ClientBookingsDTO(Long clientId, String color) {
		name = clientId.toString();
		backgroundColor = color;
		list = new ArrayList<>();
	}
	
	public ClientBookingsDTO(Long clientId, String color, BookingDTO bookingDTO) {
		this(clientId, color);
		list.add(bookingDTO);
	}
}
