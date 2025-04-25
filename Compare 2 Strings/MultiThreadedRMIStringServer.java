import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MultiThreadedRMIStringServer {
    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);

            // Create and bind the remote object
            StringCompareImpl server = new StringCompareImpl();
            Naming.rebind("rmi://localhost/StringCompare", server);

            System.out.println("Server is ready...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
