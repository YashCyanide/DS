import java.rmi.*;

public class TempClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TempClient <server_ip> <celsius_value>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/TempServer";
            TempServerIntf stub = (TempServerIntf) Naming.lookup(serverURL);

            double celsius = Double.parseDouble(args[1]);
            double fahrenheit = stub.celsiusToFahrenheit(celsius);
            System.out.println("Temperature in Fahrenheit: " + fahrenheit);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
