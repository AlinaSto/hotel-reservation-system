package spring.bookingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Review;
import spring.bookingApp.repository.HotelRepository;
import spring.bookingApp.repository.ReviewRepository;
import spring.bookingApp.repository.RoomRepository;

import java.util.List;

@Service
public class HotelService {
    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, ReviewRepository reviewRepository) {
        this.hotelRepository = hotelRepository;
        this.reviewRepository = reviewRepository;
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long hotelId) {
        Hotel foundHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "hotel not found"));
        hotelRepository.delete(foundHotel);
    }

    public List<Hotel> viewAllHotels() {
        return hotelRepository.findAll();
    }
    public Hotel findHotel(Long hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the hotel was not found"));
    }
    public List<Review> viewAllReviewsByHotel(Long hotelId) {
        Hotel foundHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "hotel not found"));
        List<Review> foundReviews = reviewRepository.findAllByHotel(foundHotel);
        return foundReviews;
    }
    public Hotel update(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}
