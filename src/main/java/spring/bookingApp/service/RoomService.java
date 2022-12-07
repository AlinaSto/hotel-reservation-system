package spring.bookingApp.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.bookingApp.dto.AddRoomDTO;
import spring.bookingApp.model.Hotel;
import spring.bookingApp.model.Room;
import spring.bookingApp.repository.HotelRepository;
import spring.bookingApp.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {
    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }


    public Room addRoomToHotel(AddRoomDTO addRoomDTO, Long hotelId) {
        Room roomToBeSaved = new Room();
        Hotel foundedHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the hotel not found"));
        roomToBeSaved.setHotel(foundedHotel);
        roomToBeSaved.setRoomNumber(addRoomDTO.getRoomNumber());
        roomToBeSaved.setPrice(addRoomDTO.getPrice());
        roomToBeSaved.setNumbersOfPersons(addRoomDTO.getNumbersOfPersons());
        return roomRepository.save(roomToBeSaved);
    }

    public List<Room> getAllRoomsByHotel(Long hotelId) {
        Hotel foundedHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the hotel not found"));
        return roomRepository.findAllByHotel(foundedHotel);
    }

    public Room updatePriceRoom(AddRoomDTO addRoomDTO, Long roomId) {
        Room foundRoom = roomRepository.findById(roomId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the room not found"));
        foundRoom.setPrice(addRoomDTO.getPrice());
        return roomRepository.save(foundRoom);
    }

    public void deleteRoomFromHotel(Long roomId) {
        Room foundRoom = roomRepository.findById(roomId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the room not found"));
        roomRepository.delete(foundRoom);
    }
}
