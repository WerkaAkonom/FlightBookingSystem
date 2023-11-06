package algorithms;
import models.City;
import models.Flight;
import models.MultiSectionRoute;

import java.util.ArrayList;

public interface FindRoute {

    ArrayList<Flight> findRouteAlgorithm(City fromCity, City toCity);
    MultiSectionRoute createRoute(City fromCity, City toCity);
}
