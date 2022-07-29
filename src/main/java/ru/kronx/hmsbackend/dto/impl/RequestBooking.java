package ru.kronx.hmsbackend.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kronx.hmsbackend.dto.TelegramMessageDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Setter
@NoArgsConstructor
public class RequestBooking implements TelegramMessageDTO {
    private static final String EMPTY_STRING = "";
    private static final int EMPTY_INT = 0;
    private static final Calendar EMPTY_CALENDAR = new GregorianCalendar(2000, 0, 01);
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Calendar dataStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Calendar dataEnd;
    private String numberType;
    private String name;
    private String surname;
    private Integer amountAdults;
    private Integer amountChildren;
    private String phoneNumber;
    private String messenger;

    private String comment;

    public RequestBooking(@JsonProperty("date-start") Calendar dataStart,
                          @JsonProperty("date-end") Calendar dataEnd,
                          @JsonProperty("type") String numberType,
                          @JsonProperty("firstName") String name,
                          @JsonProperty("lastName") String surname,
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
    public String getTelegramMessage() {
        return "<b>Новый запрос на бронирование</b>" + '\n' +
                "<b>Дата заезда: </b><i>" + getDateFormat(dataStart) + "</i>\n" +
                "<b>Дата выезда: </b><i>" + getDateFormat(dataEnd) + "</i>\n" +
                "<b>Тип номера: </b><i>" + getPhoneNumber() + "</i>\n" +
                "<b>Имя: </b><i>" + getName() + "</i>\n" +
                "<b>Фамилия: </b><i>" + getSurname() + "</i>\n" +
                "<b>Взрослых: </b><i>" + getAmountAdults() + "</i>\n" +
                "<b>Детей: </b><i>" + getAmountChildren() + "</i>\n" +
                "<b>Номер телефона: </b><i>" + getPhoneNumber() + "</i>\n" +
                "<b>Мессанжер: </b><i>" + getMessenger() + "</i>\n" +
                "<b>Коментарий: </b><i>" + getComment() + "</i>\n";
    }
}
