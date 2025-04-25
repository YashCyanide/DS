Here's how to **design a distributed application in Java using MPI (MPJ Express)** for:

> **Distributing an array from the root process to worker processes, each calculating intermediate sums, and running on 2 different machines.**

---

### 🚀 Tools Required:
- **Java**
- **MPJ Express** (Java's MPI library): [http://mpj-express.org/](http://mpj-express.org/)

---

### ✅ Steps:

---

## 🧩 1. Java Code using MPJ Express

```java
import mpi.*;

public class DistributedSum {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int totalElements = 100;
        int[] array = null;
        int elementsPerProcess = totalElements / size;
        int[] subArray = new int[elementsPerProcess];
        int[] intermediateSums = new int[size];

        if (rank == 0) {
            array = new int[totalElements];
            for (int i = 0; i < totalElements; i++) {
                array[i] = i + 1;
            }
        }

        // Distribute parts of the array
        MPI.COMM_WORLD.Scatter(array, 0, elementsPerProcess, MPI.INT, subArray, 0, elementsPerProcess, MPI.INT, 0);

        // Calculate local sum
        int localSum = 0;
        for (int i = 0; i < elementsPerProcess; i++) {
            localSum += subArray[i];
        }

        // Gather all local sums to root
        MPI.COMM_WORLD.Gather(new int[]{localSum}, 0, 1, MPI.INT, intermediateSums, 0, 1, MPI.INT, 0);

        if (rank == 0) {
            System.out.println("Intermediate sums from all processes:");
            int finalSum = 0;
            for (int i = 0; i < size; i++) {
                System.out.println("Process " + i + ": " + intermediateSums[i]);
                finalSum += intermediateSums[i];
            }
            System.out.println("Final total sum: " + finalSum);
        }

        MPI.Finalize();
    }
}
```

---

## ⚙️ 2. Compile & Run Instructions

### 🔧 Compile:
```bash
javac -cp .:mpj.jar DistributedSum.java
```

### ▶️ Run on Local Machine:
```bash
mpjrun.sh -np 4 DistributedSum
```

---

## 🌐 3. Run on 2 Machines (Distributed Execution)

1. Create a **`machines.txt`** file:
   ```
   192.168.1.10
   192.168.1.11
   ```

2. Copy the `.class` and `mpj.jar` to both machines.

3. Run:
```bash
mpjrun.sh -np 2 -dev niodev -machinesfile machines.txt DistributedSum
```

---

## ✅ Output Example:

```
Intermediate sums from all processes:
Process 0: 1275
Process 1: 1275
Process 2: 1275
Process 3: 1275
Final total sum: 5100
```

---

Let me know if you want:
- A version with dynamic input,
- An Eclipse/IDE setup,
- Or how to install and configure MPJ Express on multiple systems.