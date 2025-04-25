import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class FactorialServer {
    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);

            // Create and bind the remote object
            FactorialCalculatorImpl server = new FactorialCalculatorImpl();
            Naming.rebind("rmi://localhost/FactorialCalculator", server);

            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

