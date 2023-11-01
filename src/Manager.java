import java.util.ArrayList;
import java.util.List;

public class Manager {
    ArrayList<Passenger> passengers;
    ArrayList<Flight> flights;
    ArrayList<City> cities;
    public Manager(){
        passengers = new ArrayList<>(
                List.of(
                        
                )
        );

        flights = new ArrayList<>(
                List.of()
        );



    }
    public Flight getFlight(String fromCity, String toCity) {
        //TODO
        Flight theFlight = new Flight(new City(fromCity),new City(toCity));
        return theFlight;
    }
}
