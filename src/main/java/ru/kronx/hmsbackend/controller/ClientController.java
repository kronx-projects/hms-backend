package ru.kronx.hmsbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.entity.Client;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = clientService.getAll();
        return !clients.isEmpty() ? ResponseEntity.ok(clients) : new ResponseEntity("Нет никаких клиентов", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(clientService.findById(id));
        } catch (NoEntityException e) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Client client) {
        try {
            clientService.updateClient(client);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyRequeredFieldException ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/new")
    public ResponseEntity createCategory(@RequestBody Client client) {
        try {
            return ResponseEntity.ok(clientService.createClient(client));
        } catch (EmptyRequeredFieldException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return new ResponseEntity<String>("Некорректный id", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            clientService.deleteClient(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoEntityException ex) {
            return new ResponseEntity<String>("Удаление не произошло, Нет  id =" + id, HttpStatus.NOT_FOUND);
        }

    }
}
