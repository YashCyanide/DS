Here is the complete implementation of a **multi-threaded Echo Server** using **Java RMI**, which appends a given name to the word `"Hello"` and sends it back. This is suitable for running across **two machines**.

---

### ✅ Objective:
The **client** sends a name to the **server**, and the server responds with:  
```
Hello <name>
```

---

## 📁 Required Files

1. `EchoServerIntf.java` – Remote interface  
2. `EchoServerImpl.java` – Server-side implementation  
3. `EchoServer.java` – Server main class  
4. `EchoClient.java` – Client program  

---

### 🛠️ 1. `EchoServerIntf.java`
```java
import java.rmi.*;

public interface EchoServerIntf extends Remote {
    String sayHello(String name) throws RemoteException;
}
```

---

### 🛠️ 2. `EchoServerImpl.java`
```java
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
```

---

### 🛠️ 3. `EchoServer.java`
```java
import java.rmi.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            EchoServerImpl obj = new EchoServerImpl();
            Naming.rebind("EchoServer", obj);
            System.out.println("Echo Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
```

---

### 🛠️ 4. `EchoClient.java`
```java
import java.rmi.*;

public class EchoClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java EchoClient <server_ip> <name>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/EchoServer";
            EchoServerIntf stub = (EchoServerIntf) Naming.lookup(serverURL);

            String name = args[1];
            String message = stub.sayHello(name);
            System.out.println("Server Response: " + message);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
```

---

## ⚙️ Execution Instructions

### 🖥️ On Server Machine:

1. **Compile files**:
```bash
javac EchoServerIntf.java EchoServerImpl.java EchoServer.java
```

2. **Start RMI registry**:
```bash
start rmiregistry     # Windows
# or
rmiregistry &         # Linux/Mac
```

3. **Run the server**:
```bash
java EchoServer
```

---

### 💻 On Client Machine:

1. **Compile client files**:
```bash
javac EchoClient.java EchoServerIntf.java
```

2. **Run the client**:
```bash
java EchoClient <server_ip> Yash
```
> Replace `<server_ip>` with the actual IP of the server machine.

---

## 🧪 Example Output

**Server Console:**
```
Echo Server is running...
Thread: RMI TCP Connection(2)-192.168.1.9 - Received name: Yash
```

**Client Console:**
```
Server Response: Hello Yash
```

---

Let me know if you'd like:
- A GUI interface (Swing or JavaFX)
- To enhance with time or location info (e.g., Hello Yash at 10:30 AM)
- Or to make this part of a bigger multi-service RMI project

Need help with the next assignment?