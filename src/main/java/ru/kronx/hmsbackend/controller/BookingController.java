package ru.kronx.hmsbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.entity.*;
import ru.kronx.hmsbackend.exception.*;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.service.BookingService;
import ru.kronx.hmsbackend.service.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientBookingsDTO>> getAll() {
    	try {
    		return ResponseEntity.ok(service.getAll());
    	} catch (BookingsEmptyException e) {
    		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<ClientBookingsDTO>> findByClient(@PathVariable String clientId) {
    	try {
    		return ResponseEntity.ok(service.findByClient(clientId));
    	} catch (BookingsEmptyException e) {
    		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }
    
    @GetMapping("/{dates}")
    public ResponseEntity<List<ClientBookingsDTO>> findByDates(@PathVariable String dates) {
    	try {
    		return ResponseEntity.ok(service.findByDateBetweenStartAndEnd(dates));
    	} catch (BookingsEmptyException e) {
    		return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    	}
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Booking booking) {
        try {
            service.updateBooking(booking);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyRequeredFieldException ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/new")
    public ResponseEntity createCategory(@RequestBody Booking booking) {
        try {
            return ResponseEntity.ok(service.createBooking(booking));
        } catch (EmptyRequeredFieldException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBooking(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return new ResponseEntity<String>("Некорректный id", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            service.deleteBooking(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoEntityException ex) {
            return new ResponseEntity<String>("Удаление не произошло, Нет  id =" + id, HttpStatus.NOT_FOUND);
        }

    }
}
