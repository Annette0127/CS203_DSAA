import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] num = new long[n];
        for (int i = 0; i < n; i++) {
            num[i]  =sc.nextInt();
        }
        num = mergeSort(num,num.length);
        int count = 0;
        System.out.println(num[k-1]);

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