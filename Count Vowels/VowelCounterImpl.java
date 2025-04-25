import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VowelCounterImpl extends UnicastRemoteObject implements VowelCounter {

    public VowelCounterImpl() throws RemoteException {
        super();
    }

    @Override
    public int countVowels(String word) throws RemoteException {
        int count = 0;
        // Convert the string to lower case for easy comparison
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // Check if the character is a vowel
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }
}
