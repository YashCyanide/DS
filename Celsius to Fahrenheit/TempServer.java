import java.rmi.*;

public class TempServer {
    public static void main(String[] args) {
        try {
            TempServerImpl obj = new TempServerImpl();
            Naming.rebind("TempServer", obj);
            System.out.println("Celsius to Fahrenheit Conversion Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
        }
    }
}
