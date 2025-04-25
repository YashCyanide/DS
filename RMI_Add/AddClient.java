import java.rmi.*;

public class AddClient {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Usage: java AddClient <server_ip> <num1> <num2>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/AddServer";
            AddServerIntf addServer = (AddServerIntf) Naming.lookup(serverURL);
            double d1 = Double.parseDouble(args[1]);
            double d2 = Double.parseDouble(args[2]);
            double result = addServer.add(d1, d2);
            System.out.println("Sum: " + result);
        } catch (Exception e) {
            System.out.println("AddClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
