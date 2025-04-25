import mpi.MPI;
import mpi.*;

public class DistributedSum {
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int totalElements = 100;
        int[] array = new int[totalElements];
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
