import algorithms.Navigable;
import exceptions.DirectionNotFoundException;
import exceptions.DoubledCityException;
import exceptions.FlightNotFoundException;
import exceptions.PassengerAlreadyExistsException;
import models.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;


final class Manager implements Navigable, CityAndFlightInitializer {
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private List<Flight> flights;
    private List<City> cities;

    public Manager() {
        setUpCities();
        setUpFlights();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<City> getCities() {
        return cities;
    }

    //    public static void updatePassengers() {
//        passengers = BookingApp.getReservations().stream().map(Reservation::getPassenger).
//                collect(Collectors.toCollection(ArrayList::new));
//    }
    public void addPassenger(Passenger passenger) throws PassengerAlreadyExistsException {
        if (passengers.stream().anyMatch(c -> c.getName().equals(passenger.getName()))
                && passengers.stream().anyMatch(c -> c.getSurname().equals(passenger.getSurname()))) {
            throw new PassengerAlreadyExistsException("This Passenger already exist.");
        } else
            passengers.add(passenger);

    }

    public void setUpCities() {
        cities = new ArrayList<>(
                List.of(new City("New York"),
                        new City("Los Angeles"),
                        new City("Chicago"),
                        new City("Houston"),
                        new City("Phoenix"),
                        new City("Philadelphia"))
        );
        try {
            addCity("Detroit");
        } catch (DoubledCityException e) {
            System.out.println(e);
        }
    }

    private void addCity(String cityName) throws DoubledCityException {
        if (cities.stream().anyMatch(c -> c.getName().equals(cityName))) {
            throw new DoubledCityException("That city already exists in our database.");
        } else {
            City newCity = new City(cityName);
            cities.add(newCity);
        }
    }


    public void setUpFlights() {
        flights = new ArrayList<>(
                List.of(new Flight(cities.get(0), cities.get(2), 100),
                        new Flight(cities.get(2), cities.get(1), 100),
                        new Flight(cities.get(1), cities.get(3), 100),
                        new Flight(cities.get(3), cities.get(5), 100),
                        new Flight(cities.get(5), cities.get(4), 100))

        );
        try {
            addFlights(new City("New York"), new City("Houston"), 100);
        } catch (DirectionNotFoundException e) {
            System.out.println(e);
        }

    }

    private void addFlights(City from, City to, int seatsNum) throws DirectionNotFoundException {
        if (cities.stream().noneMatch(c -> c.getName().equals(from.getName())) ||
                cities.stream().noneMatch(c -> c.getName().equals(to.getName()))) {
            throw new DirectionNotFoundException("There is no City like this in our base.");
        } else {
            Flight newFlight = new Flight(from, to, seatsNum);
            flights.add(newFlight);
        }

    }

    public ArrayList<Flight> findRouteAlgorithm(City fromCity, City toCity) {
        ArrayList<Flight> transitFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (fromCity.getName().equals(flight.getFromCity().getName()) && flight.getFreeSeats() > 0) {
                transitFlights.add(flight);
                if (toCity.getName().equals(flight.getToCity().getName())) {
                    return transitFlights;
                }
            }
        }
        if (transitFlights.isEmpty()) {
            return transitFlights;
        }

        ArrayList<Flight> allFlights = new ArrayList<>(List.of(transitFlights.get(0)));
        ArrayList<Flight> furtherFlights = findRouteAlgorithm(transitFlights.get(0).getToCity(), toCity);
        allFlights.addAll(furtherFlights);


        for (int i = 1; i < transitFlights.size(); i++) {
            furtherFlights = findRouteAlgorithm(transitFlights.get(0).getToCity(), toCity);

            if (furtherFlights.size() != 0 && furtherFlights.size() < allFlights.size()) {
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

        if (allFlights.isEmpty()) {
            throw new FlightNotFoundException("Flight not found");
        } else {
            allFlights.forEach(c -> c.reduceSeats());
            return new MultiSectionRoute(fromCity, toCity, allFlights);
        }

    }

}
