Here's the **complete multi-threaded Java RMI-based Celsius to Fahrenheit Conversion Server** implementation that runs across **2 different machines**.

---

### ✅ Objective:
The **client** sends a temperature in **Celsius** to the server, and the **server returns** the temperature in **Fahrenheit**, using:
\[
F = C \times \frac{9}{5} + 32
\]

---

## 📁 File Structure

1. `TempServerIntf.java` – Remote interface  
2. `TempServerImpl.java` – Server implementation  
3. `TempServer.java` – Server runner  
4. `TempClient.java` – Client runner  

---

### 🛠️ 1. `TempServerIntf.java`
```java
import java.rmi.*;

public interface TempServerIntf extends Remote {
    double celsiusToFahrenheit(double celsius) throws RemoteException;
}
```

---

### 🛠️ 2. `TempServerImpl.java`
```java
import java.rmi.*;
import java.rmi.server.*;

public class TempServerImpl extends UnicastRemoteObject implements TempServerIntf {

    public TempServerImpl() throws RemoteException {
        super();
    }

    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Converting: " + celsius + "C");
        return (celsius * 9 / 5) + 32;
    }
}
```

---

### 🛠️ 3. `TempServer.java`
```java
import java.rmi.*;

public class TempServer {
    public static void main(String[] args) {
        try {
            TempServerImpl obj = new TempServerImpl();
            Naming.rebind("TempServer", obj);
            System.out.println("Celsius to Fahrenheit Conversion Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
        }
    }
}
```

---

### 🛠️ 4. `TempClient.java`
```java
import java.rmi.*;

public class TempClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TempClient <server_ip> <celsius_value>");
            return;
        }

        try {
            String serverURL = "rmi://" + args[0] + "/TempServer";
            TempServerIntf stub = (TempServerIntf) Naming.lookup(serverURL);

            double celsius = Double.parseDouble(args[1]);
            double fahrenheit = stub.celsiusToFahrenheit(celsius);
            System.out.println("Temperature in Fahrenheit: " + fahrenheit);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
```

---

## ⚙️ How to Run

### 🖥️ On **Server Machine**

1. **Compile the server-side code**:
```bash
javac TempServerIntf.java TempServerImpl.java TempServer.java
```

2. **Start RMI Registry** in the same directory:
```bash
start rmiregistry       # Windows
# or
rmiregistry &           # Linux/macOS
```

3. **Run the server**:
```bash
java TempServer
```

---

### 💻 On **Client Machine**

1. **Compile the client**:
```bash
javac TempClient.java TempServerIntf.java
```

2. **Run the client**:
```bash
java TempClient <server_ip> 37.5
```
> Example: `java TempClient 192.168.1.10 37.5`

---

## 🧪 Sample Output

**Server:**
```
Celsius to Fahrenheit Conversion Server is running...
Thread: RMI TCP Connection(2)-192.168.1.12 - Converting: 37.5C
```

**Client:**
```
Temperature in Fahrenheit: 99.5
```

---

Let me know if you'd like:
- A GUI interface with Celsius-to-Fahrenheit slider
- Bi-directional conversion support
- RESTful or WebSocket version

Want to continue with the next RMI assignment?