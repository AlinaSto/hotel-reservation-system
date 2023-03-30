package spring.bookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

  public   List<Room> findAllByHotel(Hotel hotel);

}
