import java.util.Scanner;
import static java.util.Arrays.binarySearch;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        long count = 0;
        long[] ar = new long[n];
        for (int i = 0; i < n; i++) {
            ar[i] = sc.nextLong();
        }
        mergeSort(ar, ar.length);

        long sum;
        int k, key;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                sum = m - ar[i] - ar[j];
                key = binarySearch(ar, j + 1, n, sum);
                if (key >= 0) {
                    count++;
                    k = key+1;
                    while (k < n && ar[k] == sum) {
                        k++;
                        count++;
                    }
                    k = key-1;
                    while (k > j && ar[k] == sum) {
                        count++;
                        k--;
                    }
                }
            }
        }
//
//        int j, k;
//        int rightSame = 1;
//        long right;
//        for (int i = 0; i < n; i++) {
//            k = n - 1;
//            j = i + 1;
//            long sum = m - ar[i];
//            for (; i < j && j < k; ) {
//                if (sum > ar[j] + ar[k]) j++;
//                else if (sum < ar[j] + ar[k]) k--;
//                else {
//                    count++;
//                    System.out.println(i + " " + j + " " + k + " ");
//                    if (j != k - 1 && ar[k] == ar[k - 1]) {
//                        right = ar[k];
//                        rightSame = 1;
//                        while (right == ar[k-1] && k > j+1) {
//                            rightSame++;
//                            k--;
//                            count++;
//                            System.out.println(i + " " + j + " " + k + " ");
//                        }
//                        while (j < k && ar[j] == ar[j - 1]) {
//                            count += rightSame;
//                            System.out.println(rightSame);
//                            j++;
//                        }
//                    }
//                    j++;
//                }
//            }
//        }
        System.out.println(count);
    }

    public static long[] mergeSort(long[] A, int n) {
        if (n != 1) {
            int mid = n / 2;
            long[] B = new long[mid];
            long[] C = new long[n - mid];
            for (int i = 0; i < mid; i++) {
                B[i] = A[i];
            }
            for (int i = 0; i < n - mid; i++) {
                C[i] = A[mid + i];
            }
            mergeSort(B, mid);
            mergeSort(C, n - mid);

            int in = 0;
            int l = 0;
            int r = 0;
            while (l < B.length || r < C.length) {
                if (l < B.length && r < C.length) {
                    if (B[l] < C[r]) {
                        A[in] = B[l];
                        l++;
                    } else {
                        A[in] = C[r];
                        r++;
                    }
                    in++;
                } else {
                    if (l < B.length) {
                        while (in < A.length && l < B.length) {
                            A[in] = B[l];
                            in++;
                            l++;
                        }
                    } else {
                        while (in < A.length && r < C.length) {
                            A[in] = C[r];
                            in++;
                            r++;
                        }
                    }
                }
            }
        }
        return A;
    }
}
