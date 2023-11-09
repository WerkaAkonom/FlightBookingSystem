package models;

import models.City;

public abstract class Route {
    private City fromCity;
    private City toCity;

    public Route(City fromCity, City toCity) {
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    @Override
    public String toString() {
        return "models.Route{" +
                "fromCity=" + fromCity +
                ", toCity=" + toCity +
                '}';
    }
}
