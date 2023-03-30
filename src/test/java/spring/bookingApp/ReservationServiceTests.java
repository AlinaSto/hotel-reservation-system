package spring.bookingApp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.bookingApp.dto.AddReservationDTO;
import spring.bookingApp.dto.ReservationDTO;
import spring.bookingApp.dto.ReservationResponseDTO;
import spring.bookingApp.model.*;
import spring.bookingApp.repository.ReservationRepository;
import spring.bookingApp.service.ReservationService;
import spring.bookingApp.service.RoomService;
import spring.bookingApp.service.UserService;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ReservationServiceTests {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;


    @Test
    void testAddReservation_ShouldReturnAddedReservation() {

        Room foundRoom = new Room(1L,"3", null,3,null,null);
        User foundUser = new User(1L,"username","password","email",null, null, null);
        Reservation foundReservation = null;
        Reservation reservationToBeAddded = new Reservation (1,3,null,foundUser);

        when(roomService.findRoom(any())).thenReturn(foundRoom);
        when(userService.findLoggedInUser()).thenReturn(foundUser);
        when(reservationRepository.findByRoomAndUser(any(),any())).thenReturn((List<Reservation>) foundReservation);
        when(reservationRepository.save(any())).thenReturn(reservationToBeAddded);

        ReservationDTO reservationDTO = new ReservationDTO(foundUser.getId(), foundRoom.getId());
        AddReservationDTO addReservationDTO = new AddReservationDTO(foundUser.getId(),foundRoom.getRoomReservationList(),1,3);
        ReservationResponseDTO result = reservationService.addReservation(addReservationDTO);
        //assertEquals(VoteType.UP_VOTE, result.getVoteType());
        //assertEquals(4, foundPost.getVoteCount());
    }
}
