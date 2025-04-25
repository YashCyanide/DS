import java.rmi.*;

public interface SubtractServerIntf extends Remote {
    double subtract(double a, double b) throws RemoteException;
}
