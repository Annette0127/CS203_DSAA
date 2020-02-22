import java.util.Scanner;

public class D {
    static long count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] candy = new long[n];
        for (int i = 0; i < n; i++) {
            candy[i] = sc.nextLong();
        }
        mergeSortNew(candy, n);
        System.out.println(count);


    }

    public static long[] mergeSortNew(long[] A, int n) {
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
            mergeSortNew(B, mid);
            mergeSortNew(C, n - mid);

            int in = 0;
            int l = 0;
            int r = 0;
            while (l < B.length || r < C.length) {
                if (l < B.length && r < C.length) {
                    if (B[l] < C[r]) {
                        A[in] = B[l];
                        count += (Math.abs(in - l)) * A[in];
                        l++;
                    } else {
                        A[in] = C[r];
                        count += Math.abs(B.length + r - in) * A[in];
                        r++;
                    }
                    in++;
                } else {
                    if (l < B.length) {
                        while (in < A.length && l < B.length) {
                            A[in] = B[l];
                            count += Math.abs(in - l) * A[in];
                            in++;
                            l++;
                        }
                    } else {
                        while (in < A.length && r < C.length) {
                            A[in] = C[r];
                            count += Math.abs(B.length + r - in) * A[in];
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
