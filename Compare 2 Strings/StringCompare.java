import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringCompare extends Remote {
    String compareStrings(String str1, String str2) throws RemoteException;
}
