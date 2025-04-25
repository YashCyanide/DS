Here is your complete **Java RMI-based Division Server**, implemented with **multi-threaded support**, designed to run across **2 machines** (client and server).

---

### ✅ Objective:
Client sends two numbers `a` and `b`, server returns `a / b` as the result.

---

## 📁 File Structure

1. `DivisionServerIntf.java` – Remote interface  
2. `DivisionServerImpl.java` – Server implementation  
3. `DivisionServer.java` – Server starter  
4. `DivisionClient.java` – Client runner  

---

### 🛠️ 1. `DivisionServerIntf.java`
```java
import java.rmi.*;

public interface DivisionServerIntf extends Remote {
    double divide(double a, double b) throws RemoteException;
}
```

---

### 🛠️ 2. `DivisionServerImpl.java`
```java
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
```

---

### 🛠️ 3. `DivisionServer.java`
```java
import java.rmi.*;

public class DivisionServer {
    public static void main(String[] args) {
        try {
            DivisionServerImpl obj = new DivisionServerImpl();
            Naming.rebind("DivisionServer", obj);
            System.out.println("Division Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
```

---

### 🛠️ 4. `DivisionClient.java`
```java
import java.rmi.*;

public class DivisionClient {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java DivisionClient <server_ip> <a> <b>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/DivisionServer";
            DivisionServerIntf stub = (DivisionServerIntf) Naming.lookup(serverURL);

            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);

            double result = stub.divide(a, b);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Math Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
```

---

## ⚙️ Execution Instructions

### 🖥️ On Server Machine

1. **Compile the server code**:
```bash
javac DivisionServerIntf.java DivisionServerImpl.java DivisionServer.java
```

2. **Start RMI registry**:
```bash
start rmiregistry     # Windows
# or
rmiregistry &         # Linux/Mac
```

3. **Run the server**:
```bash
java DivisionServer
```

---

### 💻 On Client Machine

1. **Compile the client code**:
```bash
javac DivisionClient.java DivisionServerIntf.java
```

2. **Run the client**:
```bash
java DivisionClient <server_ip> 100 4
```
> Replace `<server_ip>` with the actual IP address of the server.

---

## 🧪 Output Example

**Server Console:**
```
Division Server is running...
Thread: RMI TCP Connection(2)-192.168.1.8 - Dividing: 100.0 / 4.0
```

**Client Console:**
```
Result: 25.0
```

---

Let me know if you'd like:
- Exception handling for invalid inputs (e.g. strings, zero)
- Support for **multiple operations in a single interface**
- A GUI client version

Would you like help with packaging and deploying this?