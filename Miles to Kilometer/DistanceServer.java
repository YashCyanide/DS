import java.rmi.*;

public class DistanceServer {
    public static void main(String[] args) {
        try {
            DistanceServerImpl obj = new DistanceServerImpl();
            Naming.rebind("DistanceServer", obj);
            System.out.println("Miles to Kilometers Conversion Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}

