import java.util.Arrays;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int number;
        for (int i = 0; i < cases; i++) {
            number = sc.nextInt();
            long[] num = new long[number];
            for (int j = 0; j < number; j++) {
                num[j] = sc.nextInt();
            }
            num = mergeSort(num,num.length);
            Arrays.sort(num);
            if (number >= 4) {
                if (num[number - 4] == num[number - 3] || num[number - 3] == num[number - 2]) System.out.println("wa");
                else System.out.println(num[number - 3]);
            } else {
                if (num[number - 3] == num[number - 2]) System.out.println("wa");
                else System.out.println(num[number - 3]);
            }
        }

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
