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

//TODO feature nou
//sa verificam, o data pe zi, daca sunt useri care tocmai ce si-au terminat sedere intr-o camera
//daca da, sa le trimitem un mail in care sa ii anuntam ca pot lasa review
//ei apoi pot sa faca un review la hotelul respectiv

//detalii de implementare
//ne trebe cronjob ca sa verificam o dat pe zi daca exista utilizator a caror data de sfarsit de rezervare sa fie mai mica decat data actuala si sa nu fi lasat deja review
//pentru toti utilizatorii care indeplinesc criteriul, sa dam un mail in care ii anuntam ca pot lasa review

//cum verific daca utilizatorul are deja lasat review la hotel? (ca sa nu ii mai dau mail)
//caut in db (findAllByHotelAndUser) si daca gasesc orice rezultat, atunci nu il iau in considerare ca sa ii trimit mail

//ne mai trebuie un endpoint de adaugare review pentru un hotel
//un hotel are mai multe review-uri (one to many). Dar si un client are mai multe review-uri
// In review o sa avem atribute cum ar fi Confort (intre 1si 5) si inca un atribut pentur un text mai lung

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