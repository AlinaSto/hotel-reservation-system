package spring.bookingApp.dto;

import spring.bookingApp.model.ReviewType;

public class AddReviewDTO {
    private ReviewType reviewType;
    private Long hotelId;

    public AddReviewDTO(ReviewType reviewType, Long hotelId) {
        this.reviewType = reviewType;
        this.hotelId = hotelId;
    }

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(ReviewType reviewType) {
        this.reviewType = reviewType;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
