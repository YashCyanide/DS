import java.rmi.*;

public interface DivisionServerIntf extends Remote {
    double divide(double a, double b) throws RemoteException;
}
