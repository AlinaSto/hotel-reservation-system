package spring.bookingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column
    private ReviewType reviewType;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference(value = "hotel-review")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-review")
    private User user;

    @Column
    String description;


    public Review(Long id, ReviewType reviewType, Hotel hotel, User user) {
        this.id = id;
        this.reviewType = reviewType;
        this.hotel = hotel;
        this.user = user;
    }

    public Review() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(ReviewType reviewType) {
        this.reviewType = reviewType;
    }
}
