package spring.bookingApp.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddReservationDTO {

    private Long userId;
    private List<Long> roomsIds;
    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    public AddReservationDTO(Long userId, List<Long> roomsIds, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.userId = userId;
        this.roomsIds = roomsIds;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getRoomsIds() {
        if (this.roomsIds == null) {
            this.roomsIds = new ArrayList<>();
        }
            return roomsIds;
        }
    public void setRoomsIds(List<Long> roomsIds) {
        this.roomsIds = roomsIds;
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
