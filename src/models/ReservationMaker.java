package models;

public interface ReservationMaker {

    MultiSectionRoute getRoute(String fromCity, String toCity);

    void createReservation(Passenger passenger, String fromCity, String toCity);

}
