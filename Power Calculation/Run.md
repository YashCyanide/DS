Here’s a complete **multi-threaded RMI-based Power Calculation Server** implementation that computes `2^n` for a given number `n`, with client-server communication on **two different machines** using Java RMI.

---

### ✅ What it does
The **client** sends a number `n` to the **server**, and the server returns `2^n`.

---

## 📁 File Structure

1. `PowerServerIntf.java` – Remote interface  
2. `PowerServerImpl.java` – Server implementation  
3. `PowerServer.java` – Server launcher  
4. `PowerClient.java` – Client runner  

---

### 🛠️ File 1: `PowerServerIntf.java`
```java
import java.rmi.*;

public interface PowerServerIntf extends Remote {
    double calculatePower(int exponent) throws RemoteException;
}
```

---

### 🛠️ File 2: `PowerServerImpl.java`
```java
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
```

---

### 🛠️ File 3: `PowerServer.java`
```java
import java.rmi.*;

public class PowerServer {
    public static void main(String args[]) {
        try {
            PowerServerImpl obj = new PowerServerImpl();
            Naming.rebind("PowerServer", obj);
            System.out.println("Power Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
```

---

### 🛠️ File 4: `PowerClient.java`
```java
import java.rmi.*;

public class PowerClient {
    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: java PowerClient <server_ip> <exponent>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/PowerServer";
            PowerServerIntf stub = (PowerServerIntf) Naming.lookup(serverURL);

            int exponent = Integer.parseInt(args[1]);
            double result = stub.calculatePower(exponent);
            System.out.println("Result: 2^" + exponent + " = " + result);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
```

---

## ⚙️ Execution Steps

### 🖥️ On Server Machine

1. **Compile:**
```bash
javac PowerServerIntf.java PowerServerImpl.java PowerServer.java
```

2. **Start RMI Registry:**
```bash
start rmiregistry      # Windows
# or
rmiregistry &          # Linux/Mac
```

3. **Run the server:**
```bash
java PowerServer
```

---

### 💻 On Client Machine

1. **Compile:**
```bash
javac PowerClient.java PowerServerIntf.java
```

2. **Run:**
```bash
java PowerClient <server_ip> 10
```

> Example: `java PowerClient 192.168.1.10 10`

---

## 🧪 Sample Output

**Server:**
```
Power Server is running...
Thread RMI TCP Connection(2)-192.168.1.15 processing: 2^10
```

**Client:**
```
Result: 2^10 = 1024.0
```

---

Let me know if you'd like:
- Combined Arithmetic Server (add, subtract, multiply, divide, power)
- GUI with dropdown for operations
- Help with testing across LAN

Ready for the next assignment?