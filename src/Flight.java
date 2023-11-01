public class Flight{

    static int ids = 0;
    int id;
    City fromCity;
    City toCity;
    public Flight(City fromCity, City toCity){
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.id = ids++;
    }

}
