package spring.bookingApp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.Description;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer reviewCount;
    @OneToMany(mappedBy = "hotel", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference(value ="hotel-room")
    private List<Room> roomList;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "hotel=review")
    private List<Review> reviewList;


    public List<Review> getReviewList() {
        return reviewList;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Room> getRoomList() {
        if (this.roomList == null) {
            this.roomList = new ArrayList<>();
        }
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
