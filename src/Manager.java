import java.util.ArrayList;
import java.util.List;


class Manager {
    ArrayList<Passenger> passengers;
    ArrayList<Flight> flights;
    ArrayList<City> cities;
    Manager(){
//        passengers = new ArrayList<>(
//
//        );
        cities = new ArrayList<>(
                List.of(new City("New York"),
                        new City("Los Angeles"),
                        new City("Chicago"),
                        new City("Houston"),
                        new City("Phoenix"),
                        new City("Philadelphia"))
        );
        flights = new ArrayList<>(
                List.of(new Flight(cities.get(0),cities.get(2), 100),
                        new Flight(cities.get(2), cities.get(1), 100),
                        new Flight(cities.get(1), cities.get(3), 100),
                        new Flight(cities.get(3), cities.get(5), 100),
                        new Flight(cities.get(5), cities.get(4),100))

        );

    }
    public MultiSectionRout getRoute(String fromCity, String toCity) throws NullPointerException {

        ArrayList<City> transitCities = new ArrayList<>();
        String actualCity = fromCity;
        for (Flight flight : flights) {
            if (flight.fromCity.name.equals(actualCity)) {
                if (flight.toCity.name.equals(toCity) && flight.seatsNum > 0) {
                    if(transitCities.size() != 0){
                        return new MultiSectionRout(new City(fromCity), flight.toCity, transitCities);
                    }
                    return new MultiSectionRout(new City(fromCity), flight.toCity);
                }
                flight.reduceSeats();
                transitCities.add(flight.toCity);
                actualCity = flight.toCity.name;
            }
        }

        throw new NullPointerException();
//        return new MultiSectionRout(new City("Warszawa"),new City("Warszawa"));
    }

}
