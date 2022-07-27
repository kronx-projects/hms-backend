package ru.kronx.hmsbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kronx.hmsbackend.entity.HotelRoom;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
}
