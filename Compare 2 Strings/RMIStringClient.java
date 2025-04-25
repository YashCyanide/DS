import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RMIStringClient {
    public static void main(String[] args) {
        try {
            // Look up the remote object
            StringCompare stub = (StringCompare) Naming.lookup("rmi://localhost/StringCompare");

            // Take input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter first string: ");
            String str1 = scanner.nextLine();
            System.out.println("Enter second string: ");
            String str2 = scanner.nextLine();

            // Call the remote method
            String result = stub.compareStrings(str1, str2);
            System.out.println("Lexicographically largest string: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
