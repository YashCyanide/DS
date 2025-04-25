import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactorialCalculator extends Remote {
    long calculateFactorial(int number) throws RemoteException;
}
