package spring.bookingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RoomReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq",
            sequenceName = "category_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "room-roomReservation")
    @JoinColumn(name="room_id")
    private Room room;

    @ManyToOne
    @JsonBackReference(value="reservation-roomReservation")
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    @Column
    private LocalDateTime dateCreated;

    public RoomReservation(){}

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
