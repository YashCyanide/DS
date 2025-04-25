import java.rmi.*;

public class DivisionServer {
    public static void main(String[] args) {
        try {
            DivisionServerImpl obj = new DivisionServerImpl();
            Naming.rebind("DivisionServer", obj);
            System.out.println("Division Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
