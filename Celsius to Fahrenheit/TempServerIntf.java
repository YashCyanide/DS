import java.rmi.*;

public interface TempServerIntf extends Remote {
    double celsiusToFahrenheit(double celsius) throws RemoteException;
}
