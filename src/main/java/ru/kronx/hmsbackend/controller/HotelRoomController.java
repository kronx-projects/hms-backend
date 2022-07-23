package ru.kronx.hmsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.entity.HotelRoom;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.service.HotelRoomServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotel-rooms")
public class HotelRoomController {

    @Autowired
    private HotelRoomServiceImpl hotelRoomService;

    @GetMapping("/all")
    public ResponseEntity<List<HotelRoom>> getAll() {
        List<HotelRoom> hotelRooms = hotelRoomService.getAll();
        return !hotelRooms.isEmpty() ?
                ResponseEntity.ok(hotelRooms) :
                new ResponseEntity("Комнаты отсутствуют",
                        HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelRoom> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(hotelRoomService.findById(id));
        } catch (NoEntityException e) {
            return new ResponseEntity<HotelRoom>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody HotelRoom hotelRoom) {
        try {
            return ResponseEntity.ok(hotelRoomService.updateHotelRoom(hotelRoom));
        } catch (EmptyRequeredFieldException ex) {
            return new ResponseEntity<String>(ex.getMessage(),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/new")
    public ResponseEntity addNewHotelRoom(@RequestBody HotelRoom hotelRoom) {
        try {
            return ResponseEntity.ok(hotelRoomService.createHotelRoom(hotelRoom));
        } catch (EmptyRequeredFieldException e) {
            return new ResponseEntity<String>(e.getMessage(),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return new ResponseEntity<String>("Некорректный id",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            hotelRoomService.deleteHotelRoom(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoEntityException ex) {
            return new ResponseEntity<String>("Удаление не произошло, Нет  id =" + id,
                    HttpStatus.NOT_FOUND);
        }

    }

}
