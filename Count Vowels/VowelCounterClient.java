import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class VowelCounterClient {
    public static void main(String[] args) {
        try {
            // Look up the remote object
            VowelCounter stub = (VowelCounter) Naming.lookup("rmi://localhost/VowelCounter");

            // Take input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a word: ");
            String word = scanner.nextLine();

            // Call the remote method
            int vowelCount = stub.countVowels(word);
            System.out.println("Number of vowels in the word '" + word + "': " + vowelCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
