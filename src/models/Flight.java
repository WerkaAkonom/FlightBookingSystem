package models;

import exceptions.Plane;

public class Flight extends Route {
    static int ids = 0;
    int id;
    private int time;
    // TODO
    private int seatsNum;

    private Plane plane;


    public Flight(City fromCity, City toCity, int seatsNum) {
        super(fromCity, toCity);
        this.seatsNum = seatsNum;
        this.id = ids++;
    }

    public int getFreeSeats() {
        return seatsNum;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSeatsNum() {
        return seatsNum;
    }

    public void setSeatsNum(int seatsNum) {
        this.seatsNum = seatsNum;
    }

    public void reduceSeats() {
        int seats = getSeatsNum();
        seats--;
        setSeatsNum(seats);
    }

    @Override
    public String toString() {
        return "Flight {" +
                "id = " + id +
                ", time = " + time +
                ", seatsNum = " + seatsNum +
                " from " + getFromCity() + " to " + getToCity() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;

        if (id != flight.id) return false;
        if (time != flight.time) return false;
        return seatsNum == flight.seatsNum;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + time;
        result = 31 * result + seatsNum;
        return result;
    }


}
