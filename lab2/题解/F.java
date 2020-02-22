import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = sc.nextInt();
            long countl = 0, countr = 0, sum;
            long x, y;
            int r = 0, b = 0;
            long[][] beansR = new long[n][3];
            long[][] beansB = new long[n][3];
            for (int j = 0; j < n; j++) {
                x = sc.nextLong();//Blue
                y = sc.nextLong();//Red
                if (x > y) {//Blue is more
                    beansB[b][0] = x;
                    beansB[b][1] = y;
                    beansB[b][2] = y;
                    b++;
                } else {//Red is more
                    beansR[r][0] = x;
                    beansR[r][1] = y;
                    beansR[r][2] = -x;
                    r++;
                }
            }
            mergeSort(beansB, b);
            mergeSort(beansR, r);
            long red = 0;
            for (int j = 0; j < r - 1; j++) {
                countl += Math.min(beansR[j][1] + red, beansR[j + 1][0]);
                red = red + beansR[j][1] - Math.min(beansR[j][1] + red, beansR[j + 1][0]);
            }
            if (r > 0) {
                countl += Math.min(beansR[r - 1][1] + red, beansB[0][0]);
                red = red + beansR[r - 1][1] - Math.min(beansR[r - 1][1] + red, beansB[0][0]);
            }
            for (int j = 0; j < b - 1; j++) {
                countr += Math.min(beansB[j][1] + red, beansB[j + 1][0]);
                red = red + beansB[j][1] - Math.min(beansB[j][1] + red, beansB[j + 1][0]);
            }
            sum = countl + countr;
            System.out.println(sum);
        }
    }

    public static long[][] mergeSort(long[][] A, int n) {
        if (n > 1) {
            int mid = n / 2;
            long[][] B = new long[mid][2];
            long[][] C = new long[n - mid][2];
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
                    if (B[l][2] > C[r][2]) {
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
