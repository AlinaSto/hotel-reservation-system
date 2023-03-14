package spring.bookingApp.dto;

public class AddHotelDTO {

    private String noOfRooms;


    public AddHotelDTO(String roomNumber, Integer price, Integer numberOfPersons) {
        this.noOfRooms = roomNumber;
    }

    public String getRoomNumber() {
        return noOfRooms;
    }

    public void setRoomNumber(String roomNumber) {
        this.noOfRooms = roomNumber;
    }

}
