package models;

public class Reservation {
    private MultiSectionRoute multiSectionRoute;
    private Passenger passenger;

    public Reservation(String name, String surname, MultiSectionRoute multiSectionRoute) {
        this.passenger = new Passenger(name, surname);
        this.multiSectionRoute = multiSectionRoute;
    }

    public Reservation(Passenger passenger, MultiSectionRoute multiSectionRoute) {
        this.passenger = passenger;
        this.multiSectionRoute = multiSectionRoute;
    }

    public MultiSectionRoute getMultiSectionRoute() {
        return multiSectionRoute;
    }

    public void setMultiSectionRoute(MultiSectionRoute multiSectionRoute) {
        this.multiSectionRoute = multiSectionRoute;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


}
