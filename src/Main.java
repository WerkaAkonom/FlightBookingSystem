import java.io.IOException;

public class Main {

    static BookingApp bookingApp;

    public static void main(String[] args) throws IOException {

        bookingApp = new BookingApp();
        bookingApp.startApp();
        System.out.println(bookingApp.toString());

    }
}
