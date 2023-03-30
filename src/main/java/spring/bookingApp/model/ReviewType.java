package spring.bookingApp.model;

import java.util.List;

public enum ReviewType {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);


    private final int reviewValue;

    ReviewType(final int newReviewValue) {
        this.reviewValue = newReviewValue;
    }

    public int getReviewValue() {
        return reviewValue;
    }
}
