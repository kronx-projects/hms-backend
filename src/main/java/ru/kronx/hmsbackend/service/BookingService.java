package ru.kronx.hmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.Booking;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.*;
import ru.kronx.hmsbackend.repo.*;
import ru.kronx.hmsbackend.service.dto.*;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {
	private static final Long EMPTY_DEFAULT_ID = 0L;
	
	@Value("${spring.time.checkin}")
	private Long checkin;
	
	@Value("${spring.time.checkout}")
	private Long checkout;
	
    @Autowired
    private BookingRepository repository;
    
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientBookingsDTO> getAll() throws BookingsEmptyException {
    	List<Booking> bookings = repository.findAll();
    	if (bookings.isEmpty()) throw new BookingsEmptyException();
    	return mapping(bookings);
    }
    
    public List<ClientBookingsDTO> findById(Long id) throws NoEntityException {
    	try {
    		return mapping(List.of(repository.findById(id).get()));
    	} catch (NoSuchElementException e) {
    		throw new NoEntityException(id);
    	}
    }

    public List<ClientBookingsDTO> findByClient(Long clientId) throws BookingsEmptyException {
    	try {
    		return mapping(repository.findByClient(clientRepository.findById(clientId).get()));
    	} catch (NoSuchElementException e) {
    		throw new BookingsEmptyException(clientId);
    	}
    }
    

    public List<ClientBookingsDTO> findByDateBetweenStartAndEnd(Long dateStart, Long dateEnd) throws BookingsEmptyException {
    	LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateStart + checkin), ZoneOffset.UTC);
    	LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateEnd + checkout), ZoneOffset.UTC);
		List<Booking> bookings = repository.findAll().stream()
				.filter(booking -> booking.getDateStart().isBefore(end) && booking.getDateEnd().isAfter(start))
				.collect(Collectors.toList());
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
            		, booking.getDateEnd(), client.getName(), client.getSurname(), booking.getDescription());
    		if (result.containsKey(client.getId())) {
    			result.get(client.getId()).getBookings().add(bookingDTO);
    		} else {
    			result.put(client.getId(), new ClientBookingsDTO(client.getId(), "blue", bookingDTO));
    		}
    	});
        return result.values().stream().collect(Collectors.toList());
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
    
    public Long getCheckin() {
    	return checkin;
    }
    
    public Long getCheckout() {
    	return checkout;
    }
}
