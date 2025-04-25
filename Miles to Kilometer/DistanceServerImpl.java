import java.rmi.*;
import java.rmi.server.*;

public class DistanceServerImpl extends UnicastRemoteObject implements DistanceServerIntf {

    public DistanceServerImpl() throws RemoteException {
        super();
    }

    public double milesToKilometers(double miles) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Converting: " + miles + " miles");
        return miles * 1.60934;
    }
}
