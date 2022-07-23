package ru.kronx.hmsbackend.service;

import ru.kronx.hmsbackend.entity.HotelRoom;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.util.List;

public interface HotelRoomService {

    List<HotelRoom> getAll();

    HotelRoom findById(Long id) throws NoEntityException;

    HotelRoom createHotelRoom(HotelRoom hotelRoom) throws EmptyRequeredFieldException;

    HotelRoom updateHotelRoom(HotelRoom hotelRoom) throws EmptyRequeredFieldException;

    void deleteHotelRoom(Long id) throws NoEntityException;

    void checkRequiredFieldOfHotelRoom(HotelRoom hotelRoom, OperationModify operationModify) throws EmptyRequeredFieldException;

}
