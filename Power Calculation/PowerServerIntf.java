import java.rmi.*;

public interface PowerServerIntf extends Remote {
    double calculatePower(int exponent) throws RemoteException;
}
