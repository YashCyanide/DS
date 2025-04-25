import java.rmi.*;

public class EchoClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java EchoClient <server_ip> <name>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/EchoServer";
            EchoServerIntf stub = (EchoServerIntf) Naming.lookup(serverURL);

            String name = args[1];
            String message = stub.sayHello(name);
            System.out.println("Server Response: " + message);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
