import java.rmi.*;
import java.rmi.server.*;

public class SubtractServerImpl extends UnicastRemoteObject implements SubtractServerIntf {

    public SubtractServerImpl() throws RemoteException {
        super();
    }

    public double subtract(double a, double b) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Processing: " + a + " - " + b);
        return a - b;
    }
}
