package models;

import java.io.IOException;

public interface ReservationMaker {

    MultiSectionRoute getRoute(String fromCity, String toCity);

    void createReservation(Passenger passenger, String fromCity, String toCity) throws IOException;

}
