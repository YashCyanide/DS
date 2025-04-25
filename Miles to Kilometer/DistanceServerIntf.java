import java.rmi.*;

public interface DistanceServerIntf extends Remote {
    double milesToKilometers(double miles) throws RemoteException;
}

