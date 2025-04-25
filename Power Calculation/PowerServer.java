import java.rmi.*;

public class PowerServer {
    public static void main(String args[]) {
        try {
            PowerServerImpl obj = new PowerServerImpl();
            Naming.rebind("PowerServer", obj);
            System.out.println("Power Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
