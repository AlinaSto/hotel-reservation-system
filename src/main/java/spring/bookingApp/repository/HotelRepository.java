package spring.bookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bookingApp.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
