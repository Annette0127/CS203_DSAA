import java.util.Scanner;


public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        sc.nextLine();
        int count = 0;
        for (int i = 0; i < cases; i++) {
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            if (s1.length() < s2.length() || s2.length() < Math.ceil((double) s1.length() / 3)) continue;


        }
        System.out.println(count);

    }

    public static long hash(String s) {
        long x = 1;
        long result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            result = s.charAt(i) * x;
            x *= 131;
        }
        return result;
    }

    public static int[] compute_prefix_function(char[] p) {
        int m = p.length;
        int[] index = new int[m + 1];
        index[1] = 0;
        int k = 0;
        for (int q = 0; q < 2; q++) {
            while (k > 0 && p[k + 1] != p[q]) k = index[k];
            if (p[k + 1] == p[q]) k++;
            index[q] = k;
        }
        return index;
    }

    public static int maching(char T[], char P[]) {
        int n = T.length;
        int m = P.length;
        int match = 0;
        int[] index = compute_prefix_function(P);
        int q = 0;
        for (int i = 0; i < n; i++) {
            while (q > 0 && P[q + 1] != T[i]) q = index[q + 1];
            if (P[q + 1] == T[i]) q++;
            if (q == m) {
                q = index[q + 1];
            }
        }
        return match;
    }
}