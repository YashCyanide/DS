Here is your **complete implementation** of a **multi-threaded Java RMI-based Miles to Kilometers Conversion Server**, designed for **execution on two machines**.

---

### ✅ Objective:
The **client** sends a distance in **miles** to the server, and the server returns the **converted distance in kilometers**, using:
\[
\text{Kilometers} = \text{Miles} \times 1.60934
\]

---

## 📁 Required Files

1. `DistanceServerIntf.java` – Remote interface  
2. `DistanceServerImpl.java` – Server implementation  
3. `DistanceServer.java` – Server launcher  
4. `DistanceClient.java` – Client program  

---

### 🛠️ 1. `DistanceServerIntf.java`
```java
import java.rmi.*;

public interface DistanceServerIntf extends Remote {
    double milesToKilometers(double miles) throws RemoteException;
}
```

---

### 🛠️ 2. `DistanceServerImpl.java`
```java
import java.rmi.*;
import java.rmi.server.*;

public class DistanceServerImpl extends UnicastRemoteObject implements DistanceServerIntf {

    public DistanceServerImpl() throws RemoteException {
        super();
    }

    public double milesToKilometers(double miles) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Converting: " + miles + " miles");
        return miles * 1.60934;
    }
}
```

---

### 🛠️ 3. `DistanceServer.java`
```java
import java.rmi.*;

public class DistanceServer {
    public static void main(String[] args) {
        try {
            DistanceServerImpl obj = new DistanceServerImpl();
            Naming.rebind("DistanceServer", obj);
            System.out.println("Miles to Kilometers Conversion Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}
```

---

### 🛠️ 4. `DistanceClient.java`
```java
import java.rmi.*;

public class DistanceClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java DistanceClient <server_ip> <miles>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/DistanceServer";
            DistanceServerIntf stub = (DistanceServerIntf) Naming.lookup(serverURL);

            double miles = Double.parseDouble(args[1]);
            double km = stub.milesToKilometers(miles);
            System.out.println("Distance in Kilometers: " + km);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
```

---

## ⚙️ Execution Guide

### 🖥️ On Server Machine:

1. **Compile the files**:
```bash
javac DistanceServerIntf.java DistanceServerImpl.java DistanceServer.java
```

2. **Start the RMI registry**:
```bash
start rmiregistry     # Windows
# or
rmiregistry &         # Linux/macOS
```

3. **Run the server**:
```bash
java DistanceServer
```

---

### 💻 On Client Machine:

1. **Compile the files**:
```bash
javac DistanceClient.java DistanceServerIntf.java
```

2. **Run the client**:
```bash
java DistanceClient <server_ip> 5.2
```
> Replace `<server_ip>` with the actual IP address of the server.

---

## 🧪 Example Output

**Server Terminal:**
```
Miles to Kilometers Conversion Server is running...
Thread: RMI TCP Connection(2)-192.168.1.5 - Converting: 5.2 miles
```

**Client Terminal:**
```
Distance in Kilometers: 8.368568
```

---

Let me know if you'd like:
- A **bi-directional converter** (km ↔ miles)
- Web-based version using REST
- A combo server for **multiple conversions**

Want to continue with the next lab question?