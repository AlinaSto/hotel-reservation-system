package spring.bookingApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.bookingApp.dto.AddReservationDTO;
import spring.bookingApp.dto.AvailabilityOfHotelRoomsDTO;
import spring.bookingApp.dto.GetAvailabilityDTO;
import spring.bookingApp.dto.ReservationResponseDTO;
import spring.bookingApp.model.Address;
import spring.bookingApp.model.Room;
import spring.bookingApp.service.ReservationService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationResponseDTO> addReservation(@RequestBody AddReservationDTO addReservationDTO){
        return status(HttpStatus.OK).body(reservationService.addReservation(addReservationDTO));
    }
    @GetMapping("/touristRecomendations")
    public List<String> getTouristRecomendations(@RequestBody Address address) {
        return (List<String>) status(HttpStatus.OK).body(reservationService.getTouristRecomendations(String.valueOf(address)));
    }

    @GetMapping("/availability")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestBody GetAvailabilityDTO getAvailabilityDTO){
        return status(HttpStatus.OK).body(reservationService.getAvailableRooms(getAvailabilityDTO.getStartDate(), getAvailabilityDTO.getEndDate(), getAvailabilityDTO.getNumberOfPersons()));
    }
    @GetMapping("/numberOfAvailableRooms")
    public  ResponseEntity<Long> getNumberOfAvailableRooms(@RequestBody AvailabilityOfHotelRoomsDTO availabilityOfHotelRoomsDTO){
        return status(HttpStatus.OK).body(reservationService.getNumberOfAvailableRooms(availabilityOfHotelRoomsDTO.getStartDate(), availabilityOfHotelRoomsDTO.getEndDate(), availabilityOfHotelRoomsDTO.getIdHotel()));
    }
    @GetMapping("/priceForAllReservations")
    public  ResponseEntity<Long> getPriceForAllReservationsBetween(@RequestBody AvailabilityOfHotelRoomsDTO availabilityOfHotelRoomsDTO) {
        return status(HttpStatus.OK).body(reservationService.getPriceForAllReservationsBetween(availabilityOfHotelRoomsDTO.getStartDate(), availabilityOfHotelRoomsDTO.getEndDate(), availabilityOfHotelRoomsDTO.getIdHotel()));
    }

    @GetMapping("/availableRoomsOrderedByPrice")
    public ResponseEntity<List<Room>> getAvailableRoomsOrderedByPrice(@RequestBody GetAvailabilityDTO getAvailabilityDTO) {
        return status(HttpStatus.OK).body(reservationService.getAvailableRoomsOrderedByPriceBy(getAvailabilityDTO.getStartDate(), getAvailabilityDTO.getEndDate(), getAvailabilityDTO.getNumberOfPersons()));
    }
}