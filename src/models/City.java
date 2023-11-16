package models;

public class City implements IdCreator {
    private static int ids = 0;
    private int id;
    private String name;

    public City(String name) {
        this.name = name;
        setId();
    }

    public void setId() {
        id = ids++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "City {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", country = '" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;

        if (id != city.id) return false;
        if (!name.equals(city.name)) return false;
        return country.equals(city.country);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }


}
