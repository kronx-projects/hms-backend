package ru.kronx.hmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.Booking;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.BookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
	
    @Autowired
    private BookingRepository repository;

    public List<Booking> getAll() {
        return repository.findAll();
    }

    public Optional<List<Booking>> findByClient(Client client) {
        return repository.findByClient(client);
    }
    
    public List<Booking> findByDateBetweenStartAndEnd(LocalDate dateStart, LocalDate dateEnd) {
		return getAll().stream().filter(booking -> booking.getDateStart().isBefore(dateEnd) || booking.getDateEnd().isAfter(dateStart)).collect(Collectors.toList());
    }

    public Booking createBooking(Booking booking) {
        return repository.save(booking);
    }

    public void deleteBooking(Long id) throws NoEntityException {
        repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        repository.deleteById(id);
    }
}
