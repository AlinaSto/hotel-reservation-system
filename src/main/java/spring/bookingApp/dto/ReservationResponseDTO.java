package spring.bookingApp.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

public class ReservationResponseDTO {

    private List<String> touristRecommendations;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public ReservationResponseDTO(List<String> touristRecommendations, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.touristRecommendations = touristRecommendations;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public ReservationResponseDTO(){}

    public List<String> getTouristRecommendations() {
        return touristRecommendations;
    }

    public void setTouristRecommendations(List<String> touristRecommendations) {
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
