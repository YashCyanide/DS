import java.rmi.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            EchoServerImpl obj = new EchoServerImpl();
            Naming.rebind("EchoServer", obj);
            System.out.println("Echo Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
