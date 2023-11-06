import algorithms.FindRoute;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


final class Manager implements FindRoute, SetUpFlightsAndCities  {
    public static ArrayList<Passenger> passengers;
    ArrayList<Flight> flights;
    ArrayList<City> cities;
    public Manager(){
        setUpCities();
        setUpFlights();
    }
    public static void updatePassengers(){
        passengers = BookingApp.getReservations().stream().map(Reservation::getPassenger).
                collect(Collectors.toCollection(ArrayList::new));
    }
    public void  setUpCities(){
        cities = new ArrayList<>(
                List.of(new City("New York"),
                        new City("Los Angeles"),
                        new City("Chicago"),
                        new City("Houston"),
                        new City("Phoenix"),
                        new City("Philadelphia"))
        );
    }

    public void setUpFlights() {
        flights = new ArrayList<>(
                List.of(new Flight(cities.get(0),cities.get(2), 100),
                        new Flight(cities.get(2), cities.get(1), 100),
                        new Flight(cities.get(1), cities.get(3), 100),
                        new Flight(cities.get(3), cities.get(5), 100),
                        new Flight(cities.get(5), cities.get(4),100))

        );
    }

    public ArrayList<Flight> findRouteAlgorithm(City fromCity, City toCity) {
        ArrayList<Flight> transitFlights = new ArrayList<>();

        for(Flight flight : flights) {
            if(fromCity.getName().equals(flight.getFromCity().getName()) && flight.getFreeSeats()>0) {
                transitFlights.add(flight);
                if(toCity.getName().equals(flight.getToCity().getName())) {
                    return transitFlights;
                }
            }
        }
        if(transitFlights.isEmpty()){
            return transitFlights;
        }

        ArrayList<Flight> allFlights = new ArrayList<>(List.of(transitFlights.get(0)));
        ArrayList<Flight> furtherFlights = findRouteAlgorithm(transitFlights.get(0).getToCity(), toCity);
        allFlights.addAll(furtherFlights);


        for (int i = 1; i <transitFlights.size();i++) {
            furtherFlights = findRouteAlgorithm(transitFlights.get(0).getToCity(), toCity);

            if(furtherFlights.size() != 0 && furtherFlights.size() < allFlights.size()) {
                allFlights = new ArrayList<>(List.of(transitFlights.get(i))); // czyscimy liste (gdy tam sie znalazlo gorsze rozwiazanie)
                // dodaje wszystkie loty z dalszej podrozy, ktore zostaly zwrocone przez algorithm
                allFlights.addAll(furtherFlights);
            }
        }
        return allFlights;


    }

    @Override
    public MultiSectionRoute createRoute(City fromCity, City toCity) {
            ArrayList<Flight> allFlights = findRouteAlgorithm(fromCity, toCity);

        return new MultiSectionRoute(fromCity, toCity, allFlights);
    }

}
