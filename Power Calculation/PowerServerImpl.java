import java.rmi.*;
import java.rmi.server.*;

public class PowerServerImpl extends UnicastRemoteObject implements PowerServerIntf {

    public PowerServerImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculatePower(int exponent) throws RemoteException {
        System.out.println("Thread " + Thread.currentThread().getName() + " processing: 2^" + exponent);
        return Math.pow(2, exponent);
    }
}
