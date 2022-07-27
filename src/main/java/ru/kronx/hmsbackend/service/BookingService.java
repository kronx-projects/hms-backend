package ru.kronx.hmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.Booking;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.BookingRepository;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
	private static final Long EMPTY_DEFAULT_ID = 0L;
	
    @Autowired
    private BookingRepository repository;

    public List<Booking> getAll() {
        return repository.findAll();
    }
    
    public Booking findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Booking> findByClient(Client client) {
        return repository.findByClient(client);
    }
    
    public List<Booking> findByDateBetweenStartAndEnd(LocalDate dateStart, LocalDate dateEnd) {
		return getAll().stream().filter(booking -> booking.getDateStart().isBefore(dateEnd) && booking.getDateEnd().isAfter(dateStart)).collect(Collectors.toList());
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
