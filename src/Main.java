import java.io.IOException;

public class Main {

    static BookingApp bookingApp;

    public static void main(String[] args) throws IOException {

//        Manager manager = new Manager();
//        manager.setUpPlanes();

        bookingApp = new BookingApp();
        bookingApp.startApp();
        System.out.println(bookingApp.toString());

    }
}
