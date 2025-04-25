import java.rmi.*;

public class MultiplyServer {
    public static void main(String args[]) {
        try {
            MultiplyServerImpl obj = new MultiplyServerImpl();
            Naming.rebind("MultiplyServer", obj);
            System.out.println("MultiplyServer is ready.");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

