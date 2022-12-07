package spring.bookingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Room;
import spring.bookingApp.repository.HotelRepository;
import spring.bookingApp.repository.RoomRepository;

import java.util.List;

@Service
public class HotelService {
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

}
