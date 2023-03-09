package spring.bookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
    List<User> findAllByReservationList(Hotel hotel);
}
