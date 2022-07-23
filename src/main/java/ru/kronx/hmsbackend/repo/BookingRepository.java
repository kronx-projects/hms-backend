package ru.kronx.hmsbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kronx.hmsbackend.entity.Booking;
import ru.kronx.hmsbackend.entity.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	Optional<List<Booking>> findByClient(Client client);
	
	Optional<List<Booking>> findByDateStart(LocalDate dateStart);
	
	Optional<List<Booking>> findByDateEnd(LocalDate dateEnd);
}

