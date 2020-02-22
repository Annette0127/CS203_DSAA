import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int q = Math.min(sc.nextInt(),n);
        long sum = 0;
        int markq = 0, mark = 0;
        long doubl = (1 << p);
        long increase, increaseMax = 0, increaseMaxq = 0;
        long[][] contain = new long[n][3];
        for (int i = 0; i < n; i++) {
            contain[i][0] = sc.nextLong();
            contain[i][1] = sc.nextLong();
            contain[i][2] = contain[i][0] - contain[i][1];
        }
        if (q == 0) {
            for (int i = 0; i < n; i++) {
                sum += contain[i][1];
            }
        } else {
            mergeSort(contain, contain.length);
            for (int i = 0; i < q; i++) {
                increase = contain[i][0] * doubl - Math.max(contain[i][0], contain[i][1]);
                if (increase > increaseMaxq) {
                    increaseMaxq = increase;
                    markq = i;
                }
            }
            for (int i = q; i < contain.length; i++) {
                increase = contain[i][0] * doubl - contain[i][1];
                if (increase > increaseMax) {
                    increaseMax = increase;
                    mark = i;
                }
            }
            long a = increaseMaxq;
            a += (Math.max(contain[q - 1][0], contain[q - 1][1])) - contain[q - 1][1];
            a -= increaseMax;
            if (a < 0) {
                for (int i = 0; i < q - 1; i++) {
                    if (contain[i][0] > contain[i][1]) sum += contain[i][0];
                    else sum += contain[i][1];
                }
                sum += Math.max(contain[mark][0] * doubl, contain[mark][1]);
                for (int i = q - 1; i < n; i++) {
                    if (mark == i) continue;
                    else sum += contain[i][1];
                }
            } else {
                for (int i = 0; i < q; i++) {
                    if (i == markq) {
                        sum += Math.max(contain[markq][0] * doubl, contain[markq][1]);
                    } else {
                        if (contain[i][0] > contain[i][1]) sum += contain[i][0];
                        else sum += contain[i][1];
                    }
                }
                for (int i = q; i < n; i++) {
                    sum += contain[i][1];
                }
            }
        }
        System.out.println(sum);
    }

    public static long[][] mergeSort(long[][] A, int n) {
        if (n != 1) {
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