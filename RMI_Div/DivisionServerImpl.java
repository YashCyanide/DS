import java.rmi.*;
import java.rmi.server.*;

public class DivisionServerImpl extends UnicastRemoteObject implements DivisionServerIntf {

    public DivisionServerImpl() throws RemoteException {
        super();
    }

    public double divide(double a, double b) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Dividing: " + a + " / " + b);
        if (b == 0) throw new ArithmeticException("Division by zero is not allowed.");
        return a / b;
    }
}
