import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VowelCounter extends Remote {
    int countVowels(String word) throws RemoteException;
}
