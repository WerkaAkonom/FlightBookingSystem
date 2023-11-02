class Passenger {
    int id;
    String name;
    String surname;
    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger passenger)) return false;

        if (id != passenger.id) return false;
        if (!name.equals(passenger.name)) return false;
        return surname.equals(passenger.surname);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
