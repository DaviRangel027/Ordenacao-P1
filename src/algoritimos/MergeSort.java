package src.algoritimos;

import java.util.ArrayList;

public class MergeSort {
    public static void sort(ArrayList<Integer> arr) {
        mergeSort(arr, 0, arr.size() - 1);
    }

    private static void mergeSort(ArrayList<Integer> arr, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    private static void merge(ArrayList<Integer> arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        ArrayList<Integer> L = new ArrayList<>(n1);
        ArrayList<Integer> R = new ArrayList<>(n2);

        for (int i = 0; i < n1; ++i) L.add(arr.get(left + i));
        for (int j = 0; j < n2; ++j) R.add(arr.get(middle + 1 + j));

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L.get(i) <= R.get(j)) {
                arr.set(k, L.get(i));
                i++;
            } else {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr.set(k, L.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }
}