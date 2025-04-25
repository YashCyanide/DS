Here's the **full implementation** of a **multi-threaded RMI-based Subtraction Server** that can be deployed on **two different machines** for client-server communication.

---

## ✅ Requirements
- Java JDK 8 or above
- Two machines (same network or use `localhost`)
- RMI registry running
- 4 Java files

---

## 📁 File Structure

1. `SubtractServerIntf.java` – Remote interface  
2. `SubtractServerImpl.java` – Server-side implementation  
3. `SubtractServer.java` – Main server class  
4. `SubtractClient.java` – Client program  

---

## 1️⃣ SubtractServerIntf.java
```java
import java.rmi.*;

public interface SubtractServerIntf extends Remote {
    double subtract(double a, double b) throws RemoteException;
}
```

---

## 2️⃣ SubtractServerImpl.java
```java
import java.rmi.*;
import java.rmi.server.*;

public class SubtractServerImpl extends UnicastRemoteObject implements SubtractServerIntf {

    public SubtractServerImpl() throws RemoteException {
        super();
    }

    public double subtract(double a, double b) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Processing: " + a + " - " + b);
        return a - b;
    }
}
```

---

## 3️⃣ SubtractServer.java
```java
import java.rmi.*;

public class SubtractServer {
    public static void main(String args[]) {
        try {
            SubtractServerImpl obj = new SubtractServerImpl();
            Naming.rebind("SubtractServer", obj);
            System.out.println("Subtract Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
```

---

## 4️⃣ SubtractClient.java
```java
import java.rmi.*;

public class SubtractClient {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Usage: java SubtractClient <server_ip> <num1> <num2>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/SubtractServer";
            SubtractServerIntf stub = (SubtractServerIntf) Naming.lookup(serverURL);

            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);

            double result = stub.subtract(a, b);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
```

---

## ⚙️ Execution Steps

### 🖥️ On Server Machine

1. **Compile server files:**
```bash
javac SubtractServerIntf.java SubtractServerImpl.java SubtractServer.java
```

2. **Start RMI Registry:**
```bash
start rmiregistry      # Windows
# or
rmiregistry &          # Linux/Mac
```

3. **Run the server:**
```bash
java SubtractServer
```

---

### 💻 On Client Machine

1. **Copy and compile:**
```bash
javac SubtractClient.java SubtractServerIntf.java
```

2. **Run with arguments:**
```bash
java SubtractClient <server_ip> 50 23
```
> Replace `<server_ip>` with the IP address of the server.

---

## 🎯 Output Example

**Server Output:**
```
Subtract Server is running...
Thread: RMI TCP Connection(2)-192.168.1.15 - Processing: 50.0 - 23.0
```

**Client Output:**
```
Result: 27.0
```

---

Let me know if you'd like to:
- Add **logging for each client**
- Support **multiple operations in one interface**
- Turn this into a **GUI-based tool**

Want to do the **division server** next?
