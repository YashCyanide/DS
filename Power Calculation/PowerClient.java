import java.rmi.*;

public class PowerClient {
    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: java PowerClient <server_ip> <exponent>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/PowerServer";
            PowerServerIntf stub = (PowerServerIntf) Naming.lookup(serverURL);

            int exponent = Integer.parseInt(args[1]);
            double result = stub.calculatePower(exponent);
            System.out.println("Result: 2^" + exponent + " = " + result);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
