package spring.bookingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.bookingApp.dto.AddReviewDTO;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Reservation;
import spring.bookingApp.model.Review;
import spring.bookingApp.model.User;
import spring.bookingApp.repository.HotelRepository;
import spring.bookingApp.repository.ReservationRepository;
import spring.bookingApp.repository.ReviewRepository;

import java.time.LocalDateTime;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private UserService userService;
    private HotelService hotelService;
    private HotelRepository hotelRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserService userService, HotelService hotelService, HotelRepository hotelRepository, ReservationRepository reservationRepository) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.hotelService = hotelService;
        this.hotelRepository = hotelRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public Review addReview(AddReviewDTO addReviewDTO) {
        Hotel foundHotel = hotelService.findHotel(addReviewDTO.getHotelId());
        User foundUser = userService.findLoggedInUser();
        Reservation foundReservation = reservationRepository.findByUser(foundUser);
        Review foundReview = reviewRepository.findReviewByHotelAndUser(foundHotel, foundUser);
        if (foundReview == null && (foundReservation.getCheckOut().isAfter(LocalDateTime.now()))) {
            Review review = new Review();
            review.setReviewType(addReviewDTO.getReviewType());
            review.setHotel(foundHotel);
            review.setUser(foundUser);
            foundHotel.setReviewCount(foundHotel.getReviewCount() + review.getReviewType().getReviewValue());
            hotelService.update(foundHotel);
            return reviewRepository.save(review);
        } else {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, " review already registered");

        }
    }
}
