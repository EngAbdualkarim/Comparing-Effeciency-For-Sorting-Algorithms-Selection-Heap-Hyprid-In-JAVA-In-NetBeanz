package sortingalgorithms;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sortingalgorithms {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("******** Enter of Array sizes to be tested: ********");
        int arraySize=input.nextInt();
        int[] inputSizes=new int[arraySize];
        System.out.println("**************** Enter the Sizes:****************");
        for(int i=0;i<arraySize;i++) {
            inputSizes[i]=input.nextInt();
        }
        System.out.println("");

for(int i=0;i<inputSizes.length;i++) {
    System.out.println("Case :"+inputSizes[i]);
    System.out.println();
    int[] arr = generateRandomArray(inputSizes[i]);
    System.out.println("Average case:");

    long startTime = System.nanoTime();
    selectionSort(arr);
    long endTime = System.nanoTime();
    double elapsedTime = (endTime - startTime)/1000;
    System.out.println("Selection Sort Time: " + elapsedTime + " microseconds");
    startTime = System.nanoTime();
    heapSort(arr);
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Heap Sort Time: " + elapsedTime + " microseconds");
    startTime = System.nanoTime();
    hybridSort(arr, false, 0, "random");
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Hybrid Sort Time: " + elapsedTime + " microseconds");

    System.out.println();

    System.out.println("Best case");
    int[] ascending =ascendingSort(arr) ;
    startTime = System.nanoTime();
    selectionSort(ascending);
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Selection Sort Time: " + elapsedTime + " microseconds");
    startTime = System.nanoTime();
    heapSort(ascending);
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Heap Sort Time: " + elapsedTime + " microseconds");
    startTime = System.nanoTime();
    hybridSort(ascending, false, 0, "increasing");
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Hybrid Sort Time: " + elapsedTime + " microseconds");
    System.out.println();

    System.out.println("Worst case");
    int[] descending = descendingSort(ascending);
    startTime = System.nanoTime();
    selectionSort(descending);
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Selection Sort Time: " + elapsedTime + " microseconds");
    startTime = System.nanoTime();
    heapSort(descending);
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Heap Sort Time: " + elapsedTime + " microseconds");
    startTime= System.nanoTime();
    hybridSort(descending, true, 0, "decreasing");
    endTime = System.nanoTime();
    elapsedTime = (endTime - startTime)/1000;
    System.out.println("Hybrid Sort Time: " + elapsedTime + " microseconds");
    System.out.println();
}
    }


    public static void hybridSort(int[] arr, boolean hasMemoryConstraint, int inputSize, String inputDistribution) {
        if (hasMemoryConstraint) {
            selectionSort(arr);
        } else if (inputSize <= 1000 || "increasing".equals(inputDistribution) || "random".equals(inputDistribution)) {
            heapSort(arr);
        } else {
            selectionSort(arr);
        }
    }
    public static int[] selectionSort(int[] arr) {


        int n = arr.length;
        int comparisons = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }


        System.out.println("Selection Sort Comparisons: " + comparisons);

        return arr;
    }
    public static int[] heapSort(int[] arr) {

        int n = arr.length;
        int comparisons = 0;

        for (int i = n / 2 - 1; i >= 0; i--) {
            comparisons = heapify(arr, n, i, comparisons);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            comparisons = heapify(arr, i, 0, comparisons);
        }


        System.out.println("Heap Sort Comparisons: " + comparisons);
        return arr;
    }

    private static int heapify(int[] arr, int n, int i, int comparisons) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n) {
            comparisons++;
            if (arr[leftChild] > arr[largest]) {
                largest = leftChild;
            }
        }

        if (rightChild < n) {
            comparisons++;
            if (arr[rightChild] > arr[largest]) {
                largest = rightChild;
            }
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            comparisons = heapify(arr, n, largest, comparisons);
        }

        return comparisons;
    }


    public static int[] ascendingSort(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public static int[] descendingSort(int[] arr) {
        int[] descendingArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            descendingArray[i] = arr[arr.length - 1 - i];
        }
        return descendingArray;
    }


//to genrate array of
    private static int[] generateRandomArray(int size) {
        int[] randomArray = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt(); // Adjust the bound as needed
        }

        return randomArray;
    }

}
