import java.util.ArrayList;

class MultiSectionRout extends Route{
    ArrayList<City> transitCities;

    public ArrayList<City> getTransitCities() {
        return transitCities;
    }


    public MultiSectionRout(City fromCity, City toCity) {
        super(fromCity, toCity);

    }
    public MultiSectionRout(City fromCity, City toCity, ArrayList<City> transitCities) {
        super(fromCity,toCity);
        this.transitCities = transitCities;
    }
}
