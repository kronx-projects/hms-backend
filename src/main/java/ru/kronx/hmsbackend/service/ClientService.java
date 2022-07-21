package ru.kronx.hmsbackend.service;

import org.springframework.stereotype.Service;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.repo.ClientRepository;

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

    public Client createClient(Client client) {
        return repository.save(client);
    }

    public Client updateClient(Client client) throws EmptyRequeredFieldException {

        if (client.getId() == null || client.getId() == 0) {
            throw new EmptyRequeredFieldException("Некорректный Id");
        }
        if (client.getName().isEmpty() || client.getName().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите имя");
        }
        if (client.getSurname().isEmpty() || client.getSurname().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите ФИО");
        }
        if (client.getSurname().isEmpty() || client.getSurname().isBlank()) {
            throw new EmptyRequeredFieldException("Укажите ФИО");
        }

        return repository.save(client);
    }

    public void deleteClient(Long id) throws NoEntityException {

        repository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        repository.deleteById(id);

    }
}
