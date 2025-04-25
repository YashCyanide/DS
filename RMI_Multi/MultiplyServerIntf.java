import java.rmi.*;

public interface MultiplyServerIntf extends Remote {
    double multiply(double a, double b) throws RemoteException;
}
