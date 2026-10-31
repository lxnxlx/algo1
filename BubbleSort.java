import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static int[] createArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i + 1;
        }
        return arr;
    }

    public static void checkSort(int[] arr) {
        int[] expected = arr.clone();
        Arrays.sort(expected);
        bubbleSort(arr);

        if (!Arrays.equals(arr, expected)) {
            throw new AssertionError("Incorrect sorting");
        }
    }

    public static void main(String[] args) {
        checkSort(new int[]{5, 1, 3, 1, -2});
        checkSort(new int[]{});
        checkSort(new int[]{7});
        checkSort(new int[]{1, 2, 3, 4});
        checkSort(new int[]{4, 3, 2, 1});

        // Несколько запусков перед измерением для прогрева JVM.
        for (int i = 0; i < 5; i++) {
            bubbleSort(createArray(1000));
        }

        int[] sizes = {10, 100, 200, 400, 800, 1600, 3200, 6400, 12800};
        System.out.println("N; average time (ns)");

        for (int n : sizes) {
            long totalTime = 0;

            for (int repeat = 0; repeat < 3; repeat++) {
                int[] arr = createArray(n);
                long start = System.nanoTime();
                bubbleSort(arr);
                long time = System.nanoTime() - start;
                totalTime += time;

                // Проверяем результат за пределами измеряемого участка.
                for (int i = 0; i < n; i++) {
                    if (arr[i] != i + 2) {
                        throw new AssertionError("Incorrect sorting");
                    }
                }
            }

            System.out.println(n + "; " + totalTime / 3);
        }
    }
}
