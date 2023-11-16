import exceptions.CityNotFoundException;
import exceptions.FlightNotFoundException;
import models.City;
import models.Flight;
import models.MultiSectionRoute;
import models.Reservation;
import java.util.ArrayList;


class BookingApp implements Reservation.MakeReservation, StartApplication {
    private static final Manager manager;
    private static final ArrayList<Reservation> reservations;

    BookingApp() {
    }

    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    static {
        manager = new Manager();
        reservations = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Reservation r : reservations) {
            result.append(r.getPassenger().getName()).append(" ").append(r.getPassenger().getSurname()).append("\n");
            for (Flight f : r.getMultiSectionRoute().getAllFlights()) {
                result.append(f.getFromCity()).append(" ").append(f.getToCity()).append("\n");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public MultiSectionRoute getRoute(String fromCity, String toCity) {

        City from = new City(fromCity);
        City to = new City(toCity);
        if(manager.getCities().stream().anyMatch(c -> c.equals(from)) ||
                manager.getCities().stream().anyMatch(c -> c.equals(to))) {
            throw new CityNotFoundException("We don't have that city in our database");
        }
        else {
            return manager.createRoute(from, to);
        }
    }

    public void addReservation(String name, String surname, String fromCity, String toCity) {
        try {
            reservations.add(new Reservation(name, surname, getRoute(fromCity, toCity)));
        } catch (FlightNotFoundException e) {
            System.out.println(e);
        }

    }

    public void startApp() {
        try {
            addReservation("Weronika", "Akonom", "Los Angeles", "Houston");
            addReservation("Frodo", "Baggins", "Phoenix", "Houston");
        } catch (CityNotFoundException e) {
            System.out.println(e);
        }
        Manager.updatePassengers();
    }


}
//            addReservation("John", "Snow", "Chicago", "Phoenix");
