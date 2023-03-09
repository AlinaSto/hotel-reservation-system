package spring.bookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Review;
import spring.bookingApp.model.User;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByHotel(Hotel hotel);
    Review findReviewByHotelAndUser(Hotel hotel, User user);
}
