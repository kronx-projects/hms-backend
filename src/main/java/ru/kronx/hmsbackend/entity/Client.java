package ru.kronx.hmsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "clients", schema = "bookingsystem")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private static final String EMPTY_STRING = "";
    private static final Long EMPTY_DEFAULT_ID = 0L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }
    public Long getId() {
        return Optional.ofNullable(id).orElse(EMPTY_DEFAULT_ID);
    }
    public String getName() {
        return Optional.ofNullable(name).orElse(EMPTY_STRING);
    }
    public String getSurname() {
        return Optional.ofNullable(surname).orElse(EMPTY_STRING);
    }
    public String getEmail() {
        return Optional.ofNullable(email).orElse(EMPTY_STRING);
    }
    public String getPatronymic() {
        return Optional.ofNullable(patronymic).orElse(EMPTY_STRING);
    }
    public String getPhoneNumber() {
        return Optional.ofNullable(phoneNumber).orElse(EMPTY_STRING);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}