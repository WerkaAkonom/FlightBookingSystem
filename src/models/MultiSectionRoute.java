package models;

import java.util.ArrayList;

public class MultiSectionRoute extends Route {
    ArrayList<Flight> allFlights;

    public final ArrayList<Flight> getAllFlights() {
        return allFlights;
    }


    public MultiSectionRoute(City fromCity, City toCity) {
        super(fromCity, toCity);

    }

    public MultiSectionRoute(City fromCity, City toCity, ArrayList<Flight> allFlights) {
        super(fromCity, toCity);
        this.allFlights = allFlights;
    }
}
