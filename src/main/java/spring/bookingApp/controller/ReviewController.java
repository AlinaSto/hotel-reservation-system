package spring.bookingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.bookingApp.dto.AddReviewDTO;
import spring.bookingApp.model.Review;
import spring.bookingApp.service.ReviewService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody AddReviewDTO addReviewDTO){
        return status(HttpStatus.OK).body(reviewService.addReview(addReviewDTO));
    }
}
