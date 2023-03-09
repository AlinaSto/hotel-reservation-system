package spring.bookingApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column
    private String roomNumber;
    @Column
    private Integer price;
    @Column
    private Integer numbersOfPersons;

    @OneToMany(mappedBy = "room", cascade = {CascadeType.MERGE, CascadeType.PERSIST})//, CascadeType.REMOVE}, orphanRemoval = true)
    @JsonManagedReference(value ="reservation-roomReservation")
    private   List<RoomReservation> roomReservationList;

    @ManyToOne
    @JsonBackReference(value ="hotel-room")
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumbersOfPersons() {
        return numbersOfPersons;
    }

    public void setNumbersOfPersons(Integer numbersOfPersons) {
        this.numbersOfPersons = numbersOfPersons;
    }

    public List<RoomReservation> getRoomReservationList() {
        return roomReservationList;
    }

    public void setRoomReservationList(List<RoomReservation> roomReservationList) {
        this.roomReservationList = roomReservationList;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
