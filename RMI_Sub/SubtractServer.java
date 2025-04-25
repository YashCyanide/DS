import java.rmi.*;

public class SubtractServer {
    public static void main(String args[]) {
        try {
            SubtractServerImpl obj = new SubtractServerImpl();
            Naming.rebind("SubtractServer", obj);
            System.out.println("Subtract Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
