package spring.bookingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.bookingApp.dto.AddHotelDTO;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Review;
import spring.bookingApp.model.Room;
import spring.bookingApp.service.HotelService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/add")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
        return status(HttpStatus.OK).body(hotelService.addHotel(hotel));
    }

    @DeleteMapping("/delete/{hotelId}")
    public void deleteHotel(@PathVariable Long hotelId) {
        hotelService.deleteHotel(hotelId);

    }

    @GetMapping("/viewHotels")
    public ResponseEntity<List<Hotel>> viewAllHotels() {
        return status(HttpStatus.OK).body(hotelService.viewAllHotels());
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> findHotel(@PathVariable Long hotelId){
        return status(HttpStatus.OK).body(hotelService.findHotel(hotelId));
    }
    @GetMapping("/viewReviews")
    public ResponseEntity<List<Review>> viewAllReviewsByHotel(@PathVariable Long hotelId) {
        return status(HttpStatus.OK).body(hotelService.viewAllReviewsByHotel(hotelId));
    }
}