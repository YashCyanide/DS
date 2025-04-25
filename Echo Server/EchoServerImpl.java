import java.rmi.*;
import java.rmi.server.*;

public class EchoServerImpl extends UnicastRemoteObject implements EchoServerIntf {

    public EchoServerImpl() throws RemoteException {
        super();
    }

    public String sayHello(String name) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Received name: " + name);
        return "Hello " + name;
    }
}
