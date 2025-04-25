import java.rmi.*;
import java.rmi.server.*;

public class MultiplyServerImpl extends UnicastRemoteObject implements MultiplyServerIntf {
    public MultiplyServerImpl() throws RemoteException {}

    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }
}
