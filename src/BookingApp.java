
class BookingApp {
    Manager manager;
    MultiSectionRout route;
    String fromCity;
    String toCity;

    BookingApp() {
        manager = new Manager();
        fromCity = "Phoenix";
        toCity = "New York";
    }

    public void startApp(){
        try{
            route = manager.getRoute(fromCity, toCity);
        }
        catch (NullPointerException nullPointerException){
            System.out.println("Unfortunately there is no rout from " + fromCity + " to " + toCity);
        }

        route.getTransitCities().forEach(System.out::println);
        System.out.println(route.toString());
    }


}
