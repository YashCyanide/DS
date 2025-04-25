import java.rmi.*;

public class DivisionClient {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java DivisionClient <server_ip> <a> <b>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/DivisionServer";
            DivisionServerIntf stub = (DivisionServerIntf) Naming.lookup(serverURL);

            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);

            double result = stub.divide(a, b);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Math Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
