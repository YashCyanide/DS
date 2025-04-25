import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class FactorialClient {
    public static void main(String[] args) {
        try {
            // Look up the remote object
            FactorialCalculator stub = (FactorialCalculator) Naming.lookup("rmi://localhost/FactorialCalculator");

            // Take input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number to find its factorial: ");
            int number = scanner.nextInt();

            // Call the remote method
            long factorial = stub.calculateFactorial(number);
            System.out.println("The factorial of " + number + " is: " + factorial);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
