package spring.bookingApp.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class ReservationResponseDTO {

    private String touristRecommendations;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
