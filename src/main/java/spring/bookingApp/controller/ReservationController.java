package spring.bookingApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.bookingApp.dto.AddReservationDTO;
import spring.bookingApp.dto.AvailabilityOfHotelRoomsDTO;
import spring.bookingApp.dto.GetAvailabilityDTO;
import spring.bookingApp.model.Reservation;
import spring.bookingApp.model.Room;
import spring.bookingApp.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public Reservation addReservation(@RequestBody AddReservationDTO addReservationDTO){
        return reservationService.addReservation(addReservationDTO);
    }
    @GetMapping("/availability")
    public List<Room> getAvailableRooms(@RequestBody GetAvailabilityDTO getAvailabilityDTO){
        //1. de facut un DTO ca sa prindem informatiile startDate, endDate, numberOfpersons
        return reservationService.getAvailableRooms(getAvailabilityDTO.getStartDate(), getAvailabilityDTO.getEndDate(), getAvailabilityDTO.getNumberOfPersons());
    }
    @GetMapping("/numberOfAvailableRooms")
    public Long getNumberOfAvailableRooms(@RequestBody AvailabilityOfHotelRoomsDTO availabilityOfHotelRoomsDTO){
        return reservationService.getNumberOfAvailableRooms(availabilityOfHotelRoomsDTO.getStartDate(), availabilityOfHotelRoomsDTO.getEndDate(), availabilityOfHotelRoomsDTO.getIdHotel());
    }
    @GetMapping("/priceForAllReservations")
    public long getPriceForAllReservationsBetween(@RequestBody AvailabilityOfHotelRoomsDTO availabilityOfHotelRoomsDTO) {
        return reservationService.getPriceForAllReservationsBetween(availabilityOfHotelRoomsDTO.getStartDate(), availabilityOfHotelRoomsDTO.getEndDate(), availabilityOfHotelRoomsDTO.getIdHotel());
    }

    @GetMapping("/availableRoomsOrderedByPrice")
    public List<Room> getAvailableRoomsOrderedByPrice(@RequestBody GetAvailabilityDTO getAvailabilityDTO) {
        return reservationService.getAvailableRoomsOrderedByPriceBy(getAvailabilityDTO.getStartDate(), getAvailabilityDTO.getEndDate(), getAvailabilityDTO.getNumberOfPersons());
    }
}