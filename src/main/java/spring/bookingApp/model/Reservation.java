package spring.bookingApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;
    @Column
    private LocalDateTime checkIn;
    @Column
    private LocalDateTime checkOut;


    @OneToMany(mappedBy = "reservation", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<RoomReservation> roomReservationList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId")
    private User user;

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<RoomReservation> getRoomReservationList() {
        if (roomReservationList == null) {
            roomReservationList = new ArrayList<>();
        }
        return roomReservationList;
    }

    public void setRoomReservationList(List<RoomReservation> roomReservationList) {
        this.roomReservationList = roomReservationList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
