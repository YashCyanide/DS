import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StringCompareImpl extends UnicastRemoteObject implements StringCompare {

    public StringCompareImpl() throws RemoteException {
        super();
    }

    @Override
    public String compareStrings(String str1, String str2) throws RemoteException {
        if (str1.compareTo(str2) > 0) {
            return str1;
        } else {
            return str2;
        }
    }
}
