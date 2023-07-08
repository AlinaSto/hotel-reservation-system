package spring.bookingApp.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.bookingApp.dto.AddReservationDTO;
import spring.bookingApp.dto.ReservationResponseDTO;
import spring.bookingApp.model.*;
import spring.bookingApp.repository.HotelRepository;
import spring.bookingApp.repository.ReservationRepository;
import spring.bookingApp.repository.RoomRepository;
import spring.bookingApp.repository.UserRepository;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    private UserRepository userRepository;

    private RoomRepository roomRepository;

    private HotelRepository hotelRepository;
    private MailService mailService;

    private OpenAiService openAiService;
    @Autowired
    public ReservationService(OpenAiService openAiService, ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository, HotelRepository hotelRepository, MailService mailService) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.mailService = mailService;
        this.openAiService=openAiService;
    }

    public ReservationService() {
    }

    public ReservationResponseDTO addReservation(AddReservationDTO addReservationDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        List<Room> foundRooms = roomRepository.findAllById(addReservationDTO.getRoomsIds());
        if (foundRooms.size() != addReservationDTO.getRoomsIds().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one or more of the rooms was not found");
        }
        for (Room room : foundRooms) {
            if (!isAvailableRoom(room, addReservationDTO.getCheckIn(), addReservationDTO.getCheckOut())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of the rooms is not avaiable for requested period");
            }
        }

        Reservation reservation = new Reservation();
        reservation.setCheckIn(addReservationDTO.getCheckIn());
        reservation.setCheckOut(addReservationDTO.getCheckOut());
        reservation.setUser(foundUser);
        foundRooms.forEach(currentRoom -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setReservation(reservation);
            roomReservation.setRoom(currentRoom);
            roomReservation.setDateCreated(LocalDateTime.now());
            reservation.getRoomReservationList().add(roomReservation);
        });

        List<String> toursitRecommnedations = getTouristRecomendations(reservation.getRoomReservationList().get(0).getRoom().getHotel().getAddress().getCity());
        Reservation savedReservation = reservationRepository.save(reservation);
        ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();
        reservationResponseDTO.setCheckIn(savedReservation.getCheckIn());
        reservationResponseDTO.setCheckOut(savedReservation.getCheckOut());
        reservationResponseDTO.setTouristRecommendations(toursitRecommnedations);
        return reservationResponseDTO;

    }

    public List<String> getTouristRecomendations(String location) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Give me tourist attractions near " + location)
                .model("text-davinci-003")
                .echo(true)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices().stream().map(choice -> choice.getText()).collect(Collectors.toList());
    }

    public List<Room> getAvailableRooms(LocalDateTime startDate, LocalDateTime endDate, Integer numberOfPersons) {

//        List<Room> availableRooms = new ArrayList<>();
        List<Hotel> foundHotels = hotelRepository.findAll();
//        for (Hotel hotel : foundHotels) {
//            for (Room room : hotel.getRoomList()) {
//                if (room.getNumberOfPerson() == numberOfPersons) {
//                    if (isAvailableRoom(room, startDate, endDate)) {
//                        availableRooms.add(room);
//                    }
//                }
//            }
//        }
//        return availableRooms;
        return foundHotels.stream()
                .flatMap(hotel -> hotel.getRoomList().stream())
                .filter(room -> room.getNumbersOfPersons() == numberOfPersons)
                .filter(room -> isAvailableRoom(room, startDate, endDate))
                .collect(Collectors.toList());

    }

    public boolean isAvailableRoom(Room room, LocalDateTime startDate, LocalDateTime endDate) {
        if (room.getRoomReservationList().isEmpty()) {
            return true;
        } else {
            return hasRoomNonInterferingReservations(room, startDate, endDate);
        }
    }

    public boolean hasRoomNonInterferingReservations(Room room, LocalDateTime startDate, LocalDateTime endDate) {
        for (RoomReservation roomReservation : room.getRoomReservationList()) {
            if (!(roomReservation.getReservation().getCheckIn().isAfter(endDate) || roomReservation.getReservation().getCheckOut().isBefore(startDate))) {
                return false;
            }
        }
        return true;
    }

    public long getNumberOfAvailableRooms(LocalDateTime startDate, LocalDateTime endDate, Long idHotel) {
        Hotel foundHotel = hotelRepository.findById(idHotel).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the hotel was not found"));
//        int numberOfAvailableRooms = 0;
//        for (Room room : foundHotel.getRoomList()) {
//            if (isAvailableRoom(room, startDate, endDate)) {
//                numberOfAvailableRooms++;
//            }
//        }
//        return numberOfAvailableRooms;

        return foundHotel.getRoomList().stream()
                .filter(room -> isAvailableRoom(room, startDate, endDate))
                .count();
    }

    public long getPriceForAllReservationsBetween(LocalDateTime startDate, LocalDateTime endDate, Long idHotel) {
        Hotel foundHotel = hotelRepository.findById(idHotel).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the hotel was not found"));
        long totalPrice = 0;
        for (Room room : foundHotel.getRoomList()) {
            totalPrice += getPriceForARoomReservationBetween(room, startDate, endDate);
        }
        return totalPrice;
    }

    public long getPriceForARoomReservationBetween(Room room, LocalDateTime startDate, LocalDateTime endDate) {
        long totalNumberOfDaysReserved = 0;
        long numberOfDaysReserved = 0;
        for (RoomReservation roomReservation : room.getRoomReservationList()) {
            if (roomReservation.getReservation().getCheckIn().isBefore(startDate) && roomReservation.getReservation().getCheckOut().isAfter(endDate)) {
                numberOfDaysReserved = ChronoUnit.DAYS.between(startDate, endDate);
            }
            if (roomReservation.getReservation().getCheckIn().isAfter(startDate) && roomReservation.getReservation().getCheckOut().isBefore(endDate)) {
                numberOfDaysReserved = ChronoUnit.DAYS.between(roomReservation.getReservation().getCheckIn(), roomReservation.getReservation().getCheckOut());
            }
            if ((roomReservation.getReservation().getCheckIn().isAfter(startDate) && roomReservation.getReservation().getCheckIn().isBefore(endDate)) && roomReservation.getReservation().getCheckOut().isAfter(endDate)) {
                numberOfDaysReserved = ChronoUnit.DAYS.between(roomReservation.getReservation().getCheckIn(), endDate);
            }
            if (roomReservation.getReservation().getCheckIn().isBefore(startDate) && (roomReservation.getReservation().getCheckOut().isAfter(startDate) && roomReservation.getReservation().getCheckOut().isBefore(endDate))) {
                numberOfDaysReserved = ChronoUnit.DAYS.between(startDate, roomReservation.getReservation().getCheckOut());
            }
            totalNumberOfDaysReserved += numberOfDaysReserved;

        }
        return room.getPrice() * totalNumberOfDaysReserved;
    }

    public List<Room> getAvailableRoomsOrderedByPriceBy(LocalDateTime startDate, LocalDateTime endDate, Integer numberOfPersons) {
        List<Room> sortedListOfAvailableRooms = getAvailableRooms(startDate, endDate, numberOfPersons);

//        Collections.sort(sortedListOfAvailableRooms);
//        return sortedListOfAvailableRooms;
        if (sortedListOfAvailableRooms.isEmpty() || sortedListOfAvailableRooms == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no available rooms");
        }
        return sortedListOfAvailableRooms.stream()
                .sorted(Comparator.comparingInt(Room::getPrice))
                .collect(Collectors.toList());
    }

    public Map<User, Reservation> checkEndDateUsersReservations() throws MessagingException {
       // Hotel foundedHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " hotel not found"));
        Map<User, Reservation> eligibleUsersByReservations = new HashMap<>();
        List<User> allUsers =  userRepository.findAll();
        for (User user : allUsers) {
            for (Reservation reservation : user.getReservationList()) {
                //reservationRepository.findAllByCheckOutBefore(LocalDateTime.now())
                if (reservation.getCheckOut().isBefore(LocalDateTime.now()) && !isReviewAdded(user,reservation) ){
                    eligibleUsersByReservations.put(user, reservation);
                }
                //mailService.sendRateReviewMessage(user.getEmail(), foundedHotel);
            }
        }
        return eligibleUsersByReservations;
    }


    public boolean isReviewAdded (User user, Reservation reservation ){
        Hotel reservationHotel = getReservationHotel(reservation);
        return user.getReviewList().stream().anyMatch(r -> r.getHotel().equals(reservationHotel));
    }

    public Hotel getReservationHotel(Reservation reservation){
        return reservation.getRoomReservationList().get(0).getRoom().getHotel();
    }
}