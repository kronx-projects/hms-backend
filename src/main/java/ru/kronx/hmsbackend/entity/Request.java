package ru.kronx.hmsbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Setter
@NoArgsConstructor
public class Request {
    private static final String EMPTY_STRING = "";
    private static final int EMPTY_INT = 0;
    private static final Calendar EMPTY_CALENDAR = new GregorianCalendar(2000, 0, 01);
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Calendar dataStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Calendar dataEnd;
    private String numberType;
    private String name;
    private String surname;
    private Integer amountAdults;
    private Integer amountChildren;
    private String phoneNumber;
    private String messenger;

    private String comment;

    public Request(@JsonProperty("date-start") Calendar dataStart,
                   @JsonProperty("date-end") Calendar dataEnd,
                   @JsonProperty("type") String numberType,
                   @JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("adults") Integer amountAdults,
                   @JsonProperty("children") Integer amountChildren,
                   @JsonProperty("phone-number") String phoneNumber,
                   @JsonProperty("messenger") String messenger,
                   @JsonProperty("comment") String comment) {
        this.dataStart = dataStart;
        this.dataEnd = dataEnd;
        this.numberType = numberType;
        this.name = name;
        this.surname = surname;
        this.amountAdults = amountAdults;
        this.amountChildren = amountChildren;
        this.phoneNumber = phoneNumber;
        this.messenger = messenger;
        this.comment = comment;
    }

    public Calendar getDataStart() {
        return Optional.ofNullable(dataStart).orElse(EMPTY_CALENDAR);
    }

    public Calendar getDataEnd() {
        return Optional.ofNullable(dataEnd).orElse(EMPTY_CALENDAR);
    }

    public String getNumberType() {
        return Optional.ofNullable(numberType).orElse(EMPTY_STRING);
    }

    public String getName() {
        return Optional.ofNullable(name).orElse(EMPTY_STRING);
    }

    public String getSurname() {
        return Optional.ofNullable(surname).orElse(EMPTY_STRING);
    }

    public Integer getAmountAdults() {
        return Optional.ofNullable(amountAdults).orElse(EMPTY_INT);
    }

    public Integer getAmountChildren() {
        return Optional.ofNullable(amountChildren).orElse(EMPTY_INT);
    }

    public String getPhoneNumber() {
        return Optional.ofNullable(phoneNumber).orElse(EMPTY_STRING);
    }

    public String getMessenger() {
        return Optional.ofNullable(messenger).orElse(EMPTY_STRING);
    }

    public String getComment() {
        return Optional.ofNullable(comment).orElse(EMPTY_STRING);
    }

    private String getDateFormat(Calendar calendar) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        return df.format(calendar.getTime());
    }

    @Override
    public String toString() {
        return "Новый запрос на бронирование" + '\n' +
                "Дата заезда: " + getDateFormat(dataStart) + '\n' +
                "Дата выезда: " + getDateFormat(dataEnd) + '\n' +
                "Тип номера: " + numberType + '\n' +
                "Имя: " + name + '\n' +
                "Фамилия: " + surname + '\n' +
                "Детей: " + getAmountChildren() + '\n' +
                "Взрослых: " + getAmountAdults() + '\n' +
                "Номер телефона: " + phoneNumber + '\n' +
                "Мессанжер: " + messenger + '\n' +
                "Коментарий: " + comment + '\n';
    }
}
