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


    @Override
    public String toString() {
        return "City {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        City city = (City) o;
        if (this == o) return true;
        if (o == null) return false;

        if (id != city.id) return false;
        return getName().equals(city.getName());
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + getName().hashCode();
        return result;
    }
}
