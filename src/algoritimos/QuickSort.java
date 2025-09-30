package src.algoritimos;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSort {
    public static void sort(ArrayList<Integer> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }

    private static void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(ArrayList<Integer> arr, int low, int high) {
        // Implementação da Mediana de Três para escolher o pivô
        medianOfThree(arr, low, high);
        int pivot = arr.get(high);
        
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j) <= pivot) {
                i++;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);
        return i + 1;
    }

    private static void medianOfThree(ArrayList<Integer> arr, int low, int high) {
        int mid = low + (high - low) / 2;
        if (arr.get(mid) < arr.get(low)) {
            Collections.swap(arr, low, mid);
        }
        if (arr.get(high) < arr.get(low)) {
            Collections.swap(arr, low, high);
        }
        if (arr.get(high) < arr.get(mid)) {
            Collections.swap(arr, mid, high);
        }
        Collections.swap(arr, mid, high);
    }
}