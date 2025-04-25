import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FactorialCalculatorImpl extends UnicastRemoteObject implements FactorialCalculator {

    public FactorialCalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public long calculateFactorial(int number) throws RemoteException {
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
