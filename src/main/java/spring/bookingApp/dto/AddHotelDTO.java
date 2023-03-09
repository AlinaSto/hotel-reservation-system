package spring.bookingApp.dto;

public class AddHotelDTO {

    private String roomNumber;
    private Integer price;
    private Integer numberOfPersons;

    public AddHotelDTO(String roomNumber, Integer price, Integer numberOfPersons) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.numberOfPersons = numberOfPersons;
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

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
