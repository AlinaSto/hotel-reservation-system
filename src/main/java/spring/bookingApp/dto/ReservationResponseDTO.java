package spring.bookingApp.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class ReservationResponseDTO {

    private String touristRecommendations;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public ReservationResponseDTO(String touristRecommendations, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.touristRecommendations = touristRecommendations;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getTouristRecommendations() {
        return touristRecommendations;
    }

    public void setTouristRecommendations(String touristRecommendations) {
        this.touristRecommendations = touristRecommendations;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}
