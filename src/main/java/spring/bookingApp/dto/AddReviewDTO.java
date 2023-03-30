package spring.bookingApp.dto;

import spring.bookingApp.model.ReviewType;

public class AddReviewDTO {
    private ReviewType reviewType;
    private Long hotelId;

    private String description;

    public AddReviewDTO(ReviewType reviewType, Long hotelId, String description) {
        this.reviewType = reviewType;
        this.hotelId = hotelId;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
