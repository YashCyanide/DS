To implement a multi-threaded client/server communication using Java RMI (Remote Method Invocation) for counting vowels in a given word, you can follow a similar approach as in the previous example with some modifications for counting vowels.

### 1. Create a Remote Interface
Define the remote interface with the method that will be invoked remotely for counting vowels.

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VowelCounter extends Remote {
    int countVowels(String word) throws RemoteException;
}
```

### 2. Implement the Remote Interface
This class will implement the `VowelCounter` interface and provide the logic to count vowels.

```java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VowelCounterImpl extends UnicastRemoteObject implements VowelCounter {

    public VowelCounterImpl() throws RemoteException {
        super();
    }

    @Override
    public int countVowels(String word) throws RemoteException {
        int count = 0;
        // Convert the string to lower case for easy comparison
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // Check if the character is a vowel
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }
}
```

### 3. Create the Server
The server binds the `VowelCounterImpl` object to the RMI registry, making it available for the client to connect and use.

```java
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class VowelCounterServer {
    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);

            // Create and bind the remote object
            VowelCounterImpl server = new VowelCounterImpl();
            Naming.rebind("rmi://localhost/VowelCounter", server);

            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 4. Create the Client
The client will connect to the RMI server and invoke the `countVowels` method.

```java
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class VowelCounterClient {
    public static void main(String[] args) {
        try {
            // Look up the remote object
            VowelCounter stub = (VowelCounter) Naming.lookup("rmi://localhost/VowelCounter");

            // Take input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a word: ");
            String word = scanner.nextLine();

            // Call the remote method
            int vowelCount = stub.countVowels(word);
            System.out.println("Number of vowels in the word '" + word + "': " + vowelCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 5. Multi-threaded Server
The RMI server is inherently multi-threaded, as RMI uses separate threads for each client request. However, to ensure that the server can handle multiple clients simultaneously, you can use the basic RMI setup without any additional threading logic on the server side.

In case you want more explicit thread handling for each client, you can implement a thread pool or similar mechanism. However, RMI handles concurrent requests automatically.

### 6. Run the Client and Server on Different Machines

1. **On the Server Machine**:
   - Start the RMI registry:
     ```bash
     rmiregistry
     ```
   - Compile and run the server:
     ```bash
     javac VowelCounterImpl.java VowelCounterServer.java
     java VowelCounterServer
     ```

2. **On the Client Machine**:
   - Compile and run the client:
     ```bash
     javac VowelCounterClient.java
     java VowelCounterClient
     ```

Ensure that both machines are on the same network and the IP address of the server machine is used instead of `localhost` in the client's lookup method. For example:

```java
VowelCounter stub = (VowelCounter) Naming.lookup("rmi://<server-ip>/VowelCounter");
```

### Notes:
- Make sure the firewall allows RMI traffic on port 1099, or configure the firewall to allow the RMI registry port.
- Ensure that both the client and server are connected to the same network for proper communication.
- The server will process requests from multiple clients concurrently without additional code for thread management.

### Example of Expected Output

#### Server Machine:
```bash
Server is ready...
```

#### Client Machine:
```bash
Enter a word: example
Number of vowels in the word 'example': 3
```

Let me know if you need any further assistance!