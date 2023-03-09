package spring.bookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bookingApp.model.Reservation;
import spring.bookingApp.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
Reservation findByUser(User user);
List<Reservation> findAllByCheckOutBefore(LocalDateTime now);
}
