Here's how to **Develop a Distributed CORBA Application** that **Changes the Case of a String to Uppercase**, demonstrating **object brokering between 2 different machines**.

---

## 🔧 Overview

### Key Components:
1. **IDL file** – Defines the interface for the remote method.
2. **Server** – Provides implementation of the `uppercase` method.
3. **Client** – Requests the service from a different machine.
4. **ORB (Object Request Broker)** – Mediates communication between client and server.

---

## 📄 Step 1: Write the IDL File (Uppercase.idl)
```idl
module CaseApp {
  interface CaseConverter {
    string to_upper(in string str);
  };
};
```

### Compile it:
```bash
idlj -fall Uppercase.idl
```
This generates stub and skeleton Java files.

---

## 🖥️ Step 2: Server Code (Machine 1)

### ✅ CaseConverterImpl.java
```java
import CaseApp.*;

public class CaseConverterImpl extends CaseConverterPOA {
    public String to_upper(String str) {
        return str.toUpperCase();
    }
}
```

### ✅ CaseServer.java
```java
import CaseApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import java.io.*;

public class CaseServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            CaseConverterImpl converter = new CaseConverterImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(converter);
            CaseConverter href = CaseConverterHelper.narrow(ref);

            // Write IOR to a file
            PrintWriter out = new PrintWriter(new FileWriter("CaseConverterIOR.txt"));
            out.println(orb.object_to_string(href));
            out.close();

            System.out.println("Server ready and waiting...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 💻 Step 3: Client Code (Machine 2)

### ✅ CaseClient.java
```java
import CaseApp.*;
import org.omg.CORBA.*;
import java.io.*;

public class CaseClient {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            BufferedReader br = new BufferedReader(new FileReader("CaseConverterIOR.txt"));
            String ior = br.readLine();
            br.close();

            org.omg.CORBA.Object objRef = orb.string_to_object(ior);
            CaseConverter converter = CaseConverterHelper.narrow(objRef);

            String input = "distributed systems with corba";
            String result = converter.to_upper(input);

            System.out.println("Original: " + input);
            System.out.println("Uppercase: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🔄 Step 4: Run the Application on Two Machines

### On **Server Machine**:
```bash
orbd -ORBInitialPort 1050 &
javac *.java
java CaseServer -ORBInitialPort 1050 -ORBInitialHost localhost
```

> ⚠️ Ensure that `CaseConverterIOR.txt` is copied/shared with the client machine.

---

### On **Client Machine**:
```bash
javac *.java
java CaseClient -ORBInitialPort 1050 -ORBInitialHost <Server_IP>
```

---

Let me know if you want a ZIP package, a Python implementation, or script-based deployment steps for quick testing!
Great! Here’s how to switch to **Java 8** depending on your OS and tools:

---

### 🔹 **For Windows (with multiple JDKs installed)**

1. **Check installed Java versions:**
   ```bash
   java -version
   ```

2. **Set Java 8 for your terminal session:**

   Open Command Prompt and run:

   ```bash
   set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_xx
   set PATH=%JAVA_HOME%\bin;%PATH%
   java -version
   ```

   Replace `jdk1.8.0_xx` with your installed JDK 8 folder.

---

### 🔹 **For macOS (using Homebrew or manual install)**

1. **Install Java 8 using Homebrew:**
   ```bash
   brew tap homebrew/cask-versions
   brew install --cask temurin8
   ```

2. **Switch to Java 8:**
   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
   export PATH=$JAVA_HOME/bin:$PATH
   java -version
   ```

---

### 🔹 **For Linux**

1. **Install Java 8 (Ubuntu/Debian):**
   ```bash
   sudo apt update
   sudo apt install openjdk-8-jdk
   ```

2. **Set default Java version:**
   ```bash
   sudo update-alternatives --config java
   sudo update-alternatives --config javac
   ```

3. **Verify:**
   ```bash
   java -version
   ```

---

### 🔹 **For IntelliJ IDEA / Eclipse**

**In IntelliJ:**
- Go to `File > Project Structure > Project`
- Set both `Project SDK` and `Language level` to **1.8**

**In Eclipse:**
- Go to `Preferences > Java > Installed JREs`
- Add Java 8 if not listed.
- Then `Project > Properties > Java Build Path > Libraries` → select Java 8.

---

Let me know your OS or IDE if you want step-by-step for that setup.


