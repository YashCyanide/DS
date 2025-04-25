import java.rmi.*;
import java.rmi.server.*;

public class TempServerImpl extends UnicastRemoteObject implements TempServerIntf {

    public TempServerImpl() throws RemoteException {
        super();
    }

    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Converting: " + celsius + "C");
        return (celsius * 9 / 5) + 32;
    }
}
