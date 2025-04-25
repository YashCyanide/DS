import java.rmi.*;

public interface EchoServerIntf extends Remote {
    String sayHello(String name) throws RemoteException;
}

