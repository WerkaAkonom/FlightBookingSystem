import exceptions.CityNotFoundException;
import exceptions.FlightNotFoundException;
import exceptions.PassengerAlreadyExistsException;
import models.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


class BookingApp implements ReservationMaker, AppStarter {
    private static final Manager manager;
    private static final ArrayList<Reservation> reservations;

    BookingApp() {
    }

    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    static {
        try {
            manager = new Manager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        if(manager.getCities().stream().noneMatch(c -> c.getName().equals(fromCity)) ||
                manager.getCities().stream().noneMatch(c -> c.getName().equals(toCity))) {
            throw new CityNotFoundException("We don't have that city in our database");
        }
        else {
            return manager.createRoute(from, to);
        }
    }


    public void createReservation(Passenger passenger, String fromCity, String toCity ) throws IOException {
        try {
            reservations.add(new Reservation(passenger, getRoute(fromCity, toCity)));

        } catch (FlightNotFoundException e) {
            System.out.println(e);
            Files.write(Paths.get("src/exceptions/logs.txt"), ("\n" + e).getBytes(), StandardOpenOption.APPEND);

        }


    }

    public void startApp() throws IOException {
        Passenger passenger = new Passenger("Weronika", "Akonom");
        Passenger passenger1 = new Passenger("Weronika", "Akonom");
//        Passenger passenger2 = new Passenger("Frodo", "Baggins");
//        Passenger passenger3 = new Passenger("John", "Snow");
        try{
            manager.addPassenger(passenger);
            manager.addPassenger(passenger1);
        }catch(PassengerAlreadyExistsException e) {
            System.out.println(e);
            Files.write(Paths.get("src/exceptions/logs.txt"), String.valueOf("\n"+e).getBytes(), StandardOpenOption.APPEND);
        }

        try {
            createReservation(passenger, "Los Angeles", "Houston");
//            createReservation("Phoenix", "Houston");
//            createReservation("Chicago", "Phoenix");

        } catch (CityNotFoundException e) {
            System.out.println(e);
            Files.write(Paths.get("src/exceptions/logs.txt"), ("\n" + e).getBytes(), StandardOpenOption.APPEND);

        }
    }


}

