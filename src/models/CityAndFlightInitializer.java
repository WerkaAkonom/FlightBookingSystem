package models;

import java.io.IOException;

public interface CityAndFlightInitializer {

    void setUpCities() throws IOException;

    void setUpFlights() throws IOException;
}
