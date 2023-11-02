
class City {
    static int ids = 0;
    int id;
    String name;
    String country;
    public City(String name) {
        this.name = name;
        //TODO
        this.country = "USA";
        this.id = ids++;

    }
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
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
