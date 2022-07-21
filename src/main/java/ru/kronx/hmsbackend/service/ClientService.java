package ru.kronx.hmsbackend.service;

import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.ClientRepository;
import ru.kronx.hmsbackend.service.utils.OperationModify;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getAll() {
        return repository.findAll();
    }

    public Client findById(Long id) throws NoEntityException {
        return repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));
    }

    public Client createClient(Client client) throws EmptyRequeredFieldException {
        checkRequredFieldOfClient(client, OperationModify.CREATE);
        return repository.save(client);
    }

    public Client updateClient(Client client) throws EmptyRequeredFieldException {
        checkRequredFieldOfClient(client, OperationModify.UPDATE);
        return repository.save(client);
    }

    public void deleteClient(Long id) throws NoEntityException {

        repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        repository.deleteById(id);

    }

    public void checkRequredFieldOfClient(Client client, OperationModify operationModify) throws EmptyRequeredFieldException {
        if (operationModify == OperationModify.UPDATE
                && (client.getId() == null || client.getId() == 0)) {
            throw new EmptyRequeredFieldException("Некорректный Id");
        }
        if (operationModify == OperationModify.CREATE
                && (client.getId() != null && client.getId() != 0)) {
            throw new EmptyRequeredFieldException("Некорректный Id");
        }
        if (client.getName().isEmpty() || client.getName().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите имя");
        }
        if (client.getSurname().isEmpty() || client.getSurname().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите ФИО");
        }
    }
}
