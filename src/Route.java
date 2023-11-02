class Route {
    City fromCity;
    City toCity;
    public Route(City fromCity, City toCity){
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    @Override
    public String toString() {
        return "Route{" +
                "fromCity=" + fromCity +
                ", toCity=" + toCity +
                '}';
    }
}
