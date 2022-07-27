package ru.kronx.hmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.RoomType;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.RoomTypeRepository;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    private static final Long EMPTY_DEFAULT_ID = 0L;

    @Autowired
    private RoomTypeRepository repository;

    @Override
    public List<RoomType> getAll() {
        return repository.findAll();
    }

    @Override
    public RoomType findById(Long id) throws NoEntityException {
        return repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));
    }

    @Override
    public RoomType createRoomType(RoomType roomType) throws EmptyRequeredFieldException {
        checkRequiredFieldOfRoomType(roomType, OperationModify.CREATE);

        return repository.save(roomType);
    }

    @Override
    public RoomType updateRoomType(RoomType roomType) throws EmptyRequeredFieldException {
        checkRequiredFieldOfRoomType(roomType, OperationModify.UPDATE);

        return repository.save(roomType);
    }

    @Override
    public void deleteRoomType(Long id) throws NoEntityException {
        repository.deleteById(id);
    }

    @Override
    public void checkRequiredFieldOfRoomType(RoomType roomType, OperationModify operationModify) throws EmptyRequeredFieldException {
        if (operationModify == OperationModify.UPDATE
                && (roomType.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Некорректный индентификатор типа комнаты");
        }
        if (operationModify == OperationModify.CREATE
                && (!roomType.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Индентификатор генерируется автоматически");
        }
        if (roomType.getName().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите тип комнаты");
        }
    }
}
