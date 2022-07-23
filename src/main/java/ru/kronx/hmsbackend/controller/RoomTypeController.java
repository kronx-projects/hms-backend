package ru.kronx.hmsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.entity.RoomType;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.service.RoomTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room-types")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<RoomType>> getAll() {
        List<RoomType> roomTypes = roomTypeService.getAll();
        return !roomTypes.isEmpty() ?
                ResponseEntity.ok(roomTypes) :
                new ResponseEntity("Комнаты отсутствуют", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(roomTypeService.findById(id));
        } catch (NoEntityException e) {
            return new ResponseEntity<RoomType>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody RoomType roomType) {
        try {
            return ResponseEntity.ok(roomTypeService.updateRoomType(roomType));
        } catch (EmptyRequeredFieldException ex) {
            return new ResponseEntity<String>(ex.getMessage(),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/new")
    public ResponseEntity addNewHotelRoom(@RequestBody RoomType roomType) {
        try {
            return ResponseEntity.ok(roomTypeService.createRoomType(roomType));
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
            roomTypeService.deleteRoomType(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoEntityException ex) {
            return new ResponseEntity<String>("Удаление не произошло, Нет  id =" + id,
                    HttpStatus.NOT_FOUND);
        }

    }

}
