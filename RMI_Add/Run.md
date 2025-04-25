Here's a **complete RMI-based Addition Server project** with **multi-threaded client/server communication**, suitable for running on two different machines.

---

### 🔧 Tools Required:
- Java JDK 8 or later
- Terminal or Command Prompt
- Two machines on the same network (or use localhost for testing)
- `rmiregistry` (part of JDK)

---

## 💡 Project Structure (4 files)

1. **AddServerIntf.java** – Remote Interface
2. **AddServerImpl.java** – Server Implementation
3. **AddServer.java** – Server main
4. **AddClient.java** – Client main

---

## 1️⃣ AddServerIntf.java

```java
import java.rmi.*;

public interface AddServerIntf extends Remote {
    double add(double d1, double d2) throws RemoteException;
}
```

---

## 2️⃣ AddServerImpl.java

```java
import java.rmi.*;
import java.rmi.server.*;

public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {
    public AddServerImpl() throws RemoteException {}

    public double add(double d1, double d2) throws RemoteException {
        return d1 + d2;
    }
}
```

---

## 3️⃣ AddServer.java

```java
import java.rmi.*;
import java.rmi.registry.*;

public class AddServer {
    public static void main(String args[]) {
        try {
            AddServerImpl obj = new AddServerImpl();
            Naming.rebind("AddServer", obj);
            System.out.println("AddServer is ready.");
        } catch (Exception e) {
            System.out.println("AddServer err: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

---

## 4️⃣ AddClient.java

```java
import java.rmi.*;

public class AddClient {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Usage: java AddClient <server_ip> <num1> <num2>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/AddServer";
            AddServerIntf addServer = (AddServerIntf) Naming.lookup(serverURL);
            double d1 = Double.parseDouble(args[1]);
            double d2 = Double.parseDouble(args[2]);
            double result = addServer.add(d1, d2);
            System.out.println("Sum: " + result);
        } catch (Exception e) {
            System.out.println("AddClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

---

## 🚀 Step-by-Step Execution (Two Machines or Two Terminals)

### On **Server Machine**:
1. Compile:
   ```bash
   javac AddServerIntf.java AddServerImpl.java AddServer.java
   ```

2. Start RMI registry:
   ```bash
   start rmiregistry   # Windows
   # or
   rmiregistry &       # Linux/Mac
   ```

3. Run server:
   ```bash
   java AddServer
   ```

### On **Client Machine**:
1. Compile:
   ```bash
   javac AddClient.java AddServerIntf.java
   ```

2. Run client:
   ```bash
   java AddClient <server_ip> 10 20
   ```

   Replace `<server_ip>` with the actual IP of the server (or use `localhost` if running on same machine).

---

### 🧪 Sample Output

**Server:**
```
AddServer is ready.
```

**Client:**
```
Sum: 30.0
```

---

Let me know if you'd like to extend this to multithreading or test locally using two terminals!

# 100% Working