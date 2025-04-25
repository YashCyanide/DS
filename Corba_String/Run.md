To develop a **String Reversing Distributed Application using CORBA (Common Object Request Broker Architecture)** that performs operations on **two different machines**, you’ll need to:

1. Define the CORBA Interface using IDL.
2. Implement the server that provides the string reversal service.
3. Implement the client that consumes the service.
4. Deploy and run the server and client on different machines.

---

## 🧩 Steps Overview:

### ✅ 1. Define IDL Interface (`Reverse.idl`)
```idl
module ReverseApp {
  interface Reverse {
    string reverse_string(in string str);
  };
};
```

### ✅ 2. Compile the IDL File (Run on both machines)
Use the IDL compiler:
```bash
idlj -fall Reverse.idl
```
This generates the required stub and skeleton Java classes.

---

## 🖥️ Server Side (Machine 1)
### ✅ 3. `ReverseImpl.java`
```java
import ReverseApp.*;
import org.omg.CORBA.*;

public class ReverseImpl extends ReversePOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public String reverse_string(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
```

### ✅ 4. `ReverseServer.java`
```java
import ReverseApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.io.*;

public class ReverseServer {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            ReverseImpl reverseImpl = new ReverseImpl();
            reverseImpl.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(reverseImpl);
            Reverse href = ReverseHelper.narrow(ref);

            // Write IOR to a file
            FileOutputStream file = new FileOutputStream("ReverseIOR.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println(orb.object_to_string(href));
            writer.close();

            System.out.println("Server ready...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 💻 Client Side (Machine 2)
### ✅ 5. `ReverseClient.java`
```java
import ReverseApp.*;
import org.omg.CORBA.*;
import java.io.*;

public class ReverseClient {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            // Read IOR from file
            BufferedReader br = new BufferedReader(new FileReader("ReverseIOR.txt"));
            String ior = br.readLine();
            br.close();

            org.omg.CORBA.Object objRef = orb.string_to_object(ior);
            Reverse reverseRef = ReverseHelper.narrow(objRef);

            String input = "CORBA is cool";
            String reversed = reverseRef.reverse_string(input);

            System.out.println("Original: " + input);
            System.out.println("Reversed: " + reversed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🛠️ Deployment
### On **Server Machine**:
```bash
orbd -ORBInitialPort 1050 &
javac *.java
java ReverseServer -ORBInitialPort 1050 -ORBInitialHost localhost
```

### Copy `ReverseIOR.txt` to the **Client Machine**

### On **Client Machine**:
```bash
javac *.java
java ReverseClient -ORBInitialPort 1050 -ORBInitialHost <Server-IP>
```

---

Let me know if you want a Python version or using a specific ORB like JacORB or TAO.


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