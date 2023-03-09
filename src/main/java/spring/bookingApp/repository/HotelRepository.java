package spring.bookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Room;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
