package ru.kronx.hmsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "room_types", schema = "bookingsystem")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {

    private static final String EMPTY_STRING = "";
    private static final Long EMPTY_DEFAULT_ID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_name")
    private String name;

    public Long getId() {
        return Optional.ofNullable(id).orElse(EMPTY_DEFAULT_ID);
    }

    public String getName() {
        return Optional.ofNullable(name).orElse(EMPTY_STRING);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return id == roomType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
