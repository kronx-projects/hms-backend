package ru.kronx.hmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.Booking;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.*;
import ru.kronx.hmsbackend.repo.BookingRepository;
import ru.kronx.hmsbackend.service.dto.*;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingService {
	private static final Long EMPTY_DEFAULT_ID = 0L;
	
    @Autowired
    private BookingRepository repository;

    public List<ClientBookingsDTO> getAll() throws BookingsEmptyException {
    	List<Booking> bookings = repository.findAll();
    	if (bookings.isEmpty()) throw new BookingsEmptyException();
    	return mapping(bookings);
    }
    
    public Booking findById(Long id) {
        return repository.findById(id).get();
    }

    public List<ClientBookingsDTO> findByClient(String clientId) throws BookingsEmptyException {
    	List<Booking> bookings = repository.findByClientId(Long.parseLong(clientId));
    	if (bookings.isEmpty()) throw new BookingsEmptyException(clientId);
        return mapping(bookings);
    }
    
    public List<ClientBookingsDTO> findByDateBetweenStartAndEnd(String dates) throws BookingsEmptyException {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String[] stringDates = dates.split(" ");
    	LocalDate start = LocalDate.parse(stringDates[0], formatter);
    	LocalDate end = LocalDate.parse(stringDates[1], formatter);
		List<Booking> bookings = repository.findAll().stream()
				.filter(booking -> booking.getDateStart().isBefore(start) && booking.getDateEnd().isAfter(end))
				.toList();
		if (bookings.isEmpty()) throw new BookingsEmptyException(start, end);
		return mapping(bookings);
    }

    public Booking createBooking(Booking booking) throws EmptyRequeredFieldException {
    	checkRequiredFieldOfBooking(booking, OperationModify.CREATE);
        return repository.save(booking);
    }
    
    public Booking updateBooking(Booking booking) throws EmptyRequeredFieldException {
        checkRequiredFieldOfBooking(booking, OperationModify.UPDATE);
        return repository.save(booking);
    }

    public void deleteBooking(Long id) throws NoEntityException {
        repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        repository.deleteById(id);
    }
    
    private List<ClientBookingsDTO> mapping(List<Booking> bookings) {
    	Map<Long, ClientBookingsDTO> result = new HashMap<>();
    	bookings.forEach(booking -> {
    		Client client = booking.getClient();
    		BookingDTO bookingDTO = new BookingDTO(booking.getId(), booking.getDateStart()
            		, booking.getDateEnd(), client.getName(), booking.getDescription());
    		if (result.containsKey(client.getId())) {
    			result.get(client.getId()).getList().add(bookingDTO);
    		} else {
    			result.put(client.getId(), new ClientBookingsDTO(client.getId(), "blue", bookingDTO));
    		}
    	});
        return result.values().stream().toList();
    }
    
    public void checkRequiredFieldOfBooking(Booking booking, OperationModify operationModify) throws EmptyRequeredFieldException {
        if (operationModify == OperationModify.UPDATE
                && (booking.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Некорректный индентификатор бронирования");
        }
        if (operationModify == OperationModify.CREATE
                && (!booking.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Индентификатор генерируется автоматически");
        }
        if (booking.getClient() == null) {
            throw new EmptyRequeredFieldException("Укажите клиента");
        }
        if (booking.getAdults() == 0) {
            throw new EmptyRequeredFieldException("Укажите кол-во взрослых");
        }
        if (booking.getDateStart() == null) {
            throw new EmptyRequeredFieldException("Укажите дату заезда");
        }
        if (booking.getDateEnd() == null) {
            throw new EmptyRequeredFieldException("Укажите дату выезда");
        }
    }
}
