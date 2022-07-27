package ru.kronx.hmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.HotelRoom;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.HotelRoomRepository;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.util.List;

@Service
public class HotelRoomServiceImpl implements HotelRoomService {

    private static final Long EMPTY_DEFAULT_ID = 0L;
    @Autowired
    private HotelRoomRepository repository;

    @Override
    public List<HotelRoom> getAll() {
        return repository.findAll();
    }

    @Override
    public HotelRoom findById(Long id) throws NoEntityException {
        return repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));
    }

    @Override
    public HotelRoom createHotelRoom(HotelRoom hotelRoom) throws EmptyRequeredFieldException {
        checkRequiredFieldOfHotelRoom(hotelRoom, OperationModify.CREATE);
        return repository.save(hotelRoom);
    }

    @Override
    public HotelRoom updateHotelRoom(HotelRoom hotelRoom) throws EmptyRequeredFieldException {
        checkRequiredFieldOfHotelRoom(hotelRoom, OperationModify.UPDATE);
        return repository.save(hotelRoom);
    }


    @Override
    public void deleteHotelRoom(Long id) throws NoEntityException {
        repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        repository.deleteById(id);
    }

    @Override
    public void checkRequiredFieldOfHotelRoom(HotelRoom hotelRoom, OperationModify operationModify)
            throws EmptyRequeredFieldException {
        if (operationModify == OperationModify.UPDATE
                && (hotelRoom.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Некорректный индентификатор комнаты");
        }
        if (operationModify == OperationModify.CREATE
                && (!hotelRoom.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Индентификатор генерируется автоматически");
        }
        if (hotelRoom.getTitle().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите название комнаты");
        }
        if (hotelRoom.getRoomNumber() == 0) {
            throw new EmptyRequeredFieldException("укажите номер комнаты");
        }
        if (hotelRoom.getRoomType() == null) {
            throw new EmptyRequeredFieldException("укажите тип комнаты");
        }
    }
}
