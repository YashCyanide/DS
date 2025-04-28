import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class VowelCounterServer {
    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(2001);

            // Create and bind the remote object
            VowelCounterImpl server = new VowelCounterImpl();
            Naming.rebind("rmi://localhost/VowelCounter", server);

            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
