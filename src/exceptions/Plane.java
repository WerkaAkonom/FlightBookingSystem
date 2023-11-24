package exceptions;

public class Plane {
    private String model;
    private int seats;
    public Plane(String model, int seats) {
        this.model = model;
        this. seats = seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Plane{ " +
                "model= '" + model + '\'' +
                ", seats= " + seats +
                '}';
    }
}
