package ru.kronx.hmsbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.User;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.UserRepository;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private static final Long EMPTY_DEFAULT_ID = 0L;

    public List<User> getAll() {
        return repository.findAllByEnabled(true);
    }

    public User findById(Long id) throws NoEntityException, EmptyRequeredFieldException {
        return repository.findUsersById(id);
    }

    public User createUser(User user) throws EmptyRequeredFieldException {
        checkRequiredFieldOfUser(user, OperationModify.CREATE);
        return repository.save(user);
    }

    public void updateUser(User user) throws EmptyRequeredFieldException {
        checkRequiredFieldOfUser(user, OperationModify.UPDATE);
        repository.save(user);
    }

    public void enabledUser(Long id) throws NoEntityException {
        if (repository.findById(id) == null)
            throw new NoEntityException(id);
        User user = repository.findUsersById(id);
        user.setEnabled(false);
        repository.save(user);
    }

    public void checkRequiredFieldOfUser(User user, OperationModify operationModify) throws EmptyRequeredFieldException {
        if (operationModify == OperationModify.UPDATE
                && (user.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Некорректный идентификатор клиента");
        }
        if (operationModify == OperationModify.CREATE
                && (!user.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Идентификатор генерируется автоматически");
        }
        if (user.getUserName().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите имя пользователя");
        }
        if (user.getPassword().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите пароль");
        }
    }

}



