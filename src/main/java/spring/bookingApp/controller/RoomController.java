package spring.bookingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.bookingApp.dto.AddRoomDTO;
import spring.bookingApp.model.Room;
import spring.bookingApp.service.HotelService;
import spring.bookingApp.service.RoomService;

import java.util.List;

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
    public Room addRoomToHotel(@RequestBody AddRoomDTO addRoomDTO, @PathVariable Long hotelId) {
        return roomService.addRoomToHotel(addRoomDTO, hotelId);
    }

    @DeleteMapping("/delete/{roomId}")
    public void deleteRoomFromHotel(@PathVariable Long roomId) {
        roomService.deleteRoomFromHotel(roomId);

    }

    @GetMapping("/{hotelId}")
    public List<Room> getAllRoomsByHotel(@PathVariable Long hotelId) {
        return roomService.getAllRoomsByHotel(hotelId);
    }

    @PutMapping("/update/{roomId}")
    public Room updatePriceRoom(@RequestBody AddRoomDTO addRoomDTO,@PathVariable Long roomId) {
       return roomService.updatePriceRoom(addRoomDTO, roomId);
    }
}