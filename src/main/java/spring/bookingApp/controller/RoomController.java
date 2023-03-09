package spring.bookingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.bookingApp.dto.AddRoomDTO;
import spring.bookingApp.model.Room;
import spring.bookingApp.service.HotelService;
import spring.bookingApp.service.RoomService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;
@RestController
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;
    private HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

    @PostMapping("/add/{hotelId}")
    public ResponseEntity<Room> addRoomToHotel(@RequestBody AddRoomDTO addRoomDTO, @PathVariable Long hotelId) {
        return status(HttpStatus.OK).body(roomService.addRoomToHotel(addRoomDTO, hotelId));
    }

    @DeleteMapping("/delete/{roomId}")
    public void deleteRoomFromHotel(@PathVariable Long roomId) {
        roomService.deleteRoomFromHotel(roomId);

    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<Room>> getAllRoomsByHotel(@PathVariable Long hotelId) {
        return status(HttpStatus.OK).body(roomService.getAllRoomsByHotel(hotelId));
    }


    @PutMapping("/update/{roomId}")
    public ResponseEntity<Room> updateRoomPrice(@RequestBody AddRoomDTO addRoomDTO, @PathVariable Long roomId) {
       return status(HttpStatus.OK).body(roomService.updatePriceRoom(addRoomDTO, roomId));
    }
}