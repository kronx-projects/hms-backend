package ru.kronx.hmsbackend.service;

import ru.kronx.hmsbackend.entity.HotelRoom;
import ru.kronx.hmsbackend.entity.RoomType;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.util.List;

public interface RoomTypeService {

    List<RoomType> getAll();

    RoomType findById(Long id) throws NoEntityException;

    RoomType createRoomType(RoomType roomType) throws EmptyRequeredFieldException;

    RoomType updateRoomType(RoomType roomType) throws EmptyRequeredFieldException;

    void deleteRoomType(Long id) throws NoEntityException;

    void checkRequiredFieldOfRoomType(RoomType roomType, OperationModify operationModify) throws EmptyRequeredFieldException;

}
