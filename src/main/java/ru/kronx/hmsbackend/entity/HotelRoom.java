package ru.kronx.hmsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "hotel_rooms", schema = "bookingsystem")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelRoom {

    private static final String EMPTY_STRING = "";
    private static final Long EMPTY_DEFAULT_ID = 0L;
    private static final int EMPTY_INT = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "title", length = 25)
    private String title;

    @OneToOne
    @JoinColumn(name = "type_id")
    private RoomType roomType;

    public Long getId() {
        return Optional.ofNullable(id).orElse(EMPTY_DEFAULT_ID);
    }

    public int getRoomNumber() {
        return Optional.ofNullable(roomNumber).orElse(EMPTY_INT);
    }

    public String getTitle() {
        return Optional.ofNullable(title).orElse(EMPTY_STRING);
    }

    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoom hotelRoom = (HotelRoom) o;
        return id == hotelRoom.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
