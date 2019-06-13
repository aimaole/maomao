package main.java.com.test;

/**
 * 测设类
 */
public class Test2 {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4, 5, 9, 8};
        sort(0, arr.length - 1, arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int low, int high, int[] arr) {
        int i;
        int tmp;
        for (i = 0; i < arr.length-2; i++) {
            if (arr[i] > arr[i + 1]) {
                tmp = arr[i];
                arr[1] = arr[i + 1];
                arr[i + 1] = tmp;
            }

        }
        return;
    }
}
