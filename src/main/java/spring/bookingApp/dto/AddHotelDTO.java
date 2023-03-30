package spring.bookingApp.dto;

import spring.bookingApp.model.Address;

public class AddHotelDTO {

    private String noOfRooms;
    private Address address;

    public AddHotelDTO(String roomNumber,Address address) {
        this.noOfRooms = roomNumber;
        this.address = address;
    }

    public String getRoomNumber() {
        return noOfRooms;
    }

    public void setRoomNumber(String roomNumber) {
        this.noOfRooms = roomNumber;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
