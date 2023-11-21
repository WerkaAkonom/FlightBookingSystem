import exceptions.CityNotFoundException;
import exceptions.FlightNotFoundException;
import exceptions.PassengerAlreadyExistsException;
import models.*;

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
        if(manager.getCities().stream().noneMatch(c -> c.getName().equals(fromCity)) ||
                manager.getCities().stream().noneMatch(c -> c.getName().equals(toCity))) {
            throw new CityNotFoundException("We don't have that city in our database");
        }
        else {
            return manager.createRoute(from, to);
        }
    }


    public void createReservation(Passenger passenger, String fromCity, String toCity ) {
        try {
            reservations.add(new Reservation(passenger, getRoute(fromCity, toCity)));

        } catch (FlightNotFoundException e) {
            System.out.println(e);
        }


    }

    public void startApp() {
        Passenger passenger = new Passenger("Weronika", "Akonom");
        Passenger passenger1 = new Passenger("Weronika", "Akonom");
        try{
            manager.addPassenger(passenger);
            manager.addPassenger(passenger1);
        }catch(PassengerAlreadyExistsException e) {
            System.out.println(e);
        }

        try {
            createReservation(passenger, "Los Angeles", "Houston");
//            addReservation("Frodo", "Baggins", "Phoenix", "Houston");
        } catch (CityNotFoundException e) {
            System.out.println(e);
        }
//        Manager.updatePassengers();
    }


}
//            addReservation("John", "Snow", "Chicago", "Phoenix");
