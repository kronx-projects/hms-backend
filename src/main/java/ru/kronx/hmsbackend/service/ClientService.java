package ru.kronx.hmsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.ClientRepository;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.util.List;

@Service
public class ClientService {
    private static final Long EMPTY_DEFAULT_ID = 0L;
    @Autowired
    private ClientRepository repository;

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client findById(Long id) throws NoEntityException {
        return repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));
    }

    public Client createClient(Client client) throws EmptyRequeredFieldException {
        checkRequiredFieldOfClient(client, OperationModify.CREATE);
        return repository.save(client);
    }

    public Client updateClient(Client client) throws EmptyRequeredFieldException {
        checkRequiredFieldOfClient(client, OperationModify.UPDATE);
        return repository.save(client);
    }

    public void deleteClient(Long id) throws NoEntityException {
        repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        repository.deleteById(id);
    }

    public void checkRequiredFieldOfClient(Client client, OperationModify operationModify) throws EmptyRequeredFieldException {
        if (operationModify == OperationModify.UPDATE
                && (client.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Некорректный индентификатор клиента");
        }
        if (operationModify == OperationModify.CREATE
                && (!client.getId().equals(EMPTY_DEFAULT_ID))) {
            throw new EmptyRequeredFieldException("Индентификатор генерируется автоматически");
        }
        if (client.getName().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите имя");
        }
        if (client.getSurname().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите фамилию");
        }
    }
}
