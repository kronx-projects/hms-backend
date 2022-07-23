package ru.kronx.hmsbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kronx.hmsbackend.entity.Booking;
import ru.kronx.hmsbackend.entity.Client;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByClient(Client client);

}

