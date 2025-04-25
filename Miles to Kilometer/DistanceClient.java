import java.rmi.*;

public class DistanceClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java DistanceClient <server_ip> <miles>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/DistanceServer";
            DistanceServerIntf stub = (DistanceServerIntf) Naming.lookup(serverURL);

            double miles = Double.parseDouble(args[1]);
            double km = stub.milesToKilometers(miles);
            System.out.println("Distance in Kilometers: " + km);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
