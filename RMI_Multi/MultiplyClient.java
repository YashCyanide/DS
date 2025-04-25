import java.rmi.*;

public class MultiplyClient {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Usage: java MultiplyClient <server_ip> <num1> <num2>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/MultiplyServer";
            MultiplyServerIntf stub = (MultiplyServerIntf) Naming.lookup(serverURL);
            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);
            double result = stub.multiply(a, b);
            System.out.println("Product: " + result);
        } catch (Exception e) {
            System.out.println("Client Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

