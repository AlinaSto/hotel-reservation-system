package spring.bookingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/add")
    public Hotel addRoomToHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }
}

