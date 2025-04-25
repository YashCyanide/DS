import java.rmi.*;

public class SubtractClient {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Usage: java SubtractClient <server_ip> <num1> <num2>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/SubtractServer";
            SubtractServerIntf stub = (SubtractServerIntf) Naming.lookup(serverURL);

            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);

            double result = stub.subtract(a, b);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
