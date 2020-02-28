import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        sc.nextLine();
        int count = 0;
        for (int i = 0; i < cases; i++) {
            String s1 = sc.next();
            String s2 = sc.next();
            if (s2.length() < (int) Math.ceil((double) s1.length() / 3)) continue;
            int maxSubLength = maching(s2, s1);
            if (maxSubLength >= (int) Math.ceil((double) s1.length() / 3)) count++;
        }
        System.out.println(count);

    }

    public static int[] compute_prefix_function(String p) {
        int m = p.length();
        int[] index = new int[m];
        index[0] = 0;
        int k = 0;
        for (int q = 1; q < p.length(); q++) {
            while (k > 0 && p.charAt(k) != p.charAt(q)) k = index[k - 1];
            if (p.charAt(k) == p.charAt(q)) k++;
            index[q] = k;
        }
        return index;
    }

    public static int maching(String T, String P) {
        int n = T.length();
        int m = P.length();
        int match = 0;
        int[] index = compute_prefix_function(P);
        int q = 0;
        for (int i = 0; i < n; i++) {
            while (q > 0 && P.charAt(q) != T.charAt(i)) q = index[q - 1];
            if (P.charAt(q) == T.charAt(i)) {
                q++;
                match = q > match ? q : match;
            }
            if (q == m) {
                q = index[q - 1];
            }
        }
        return match;
    }
}
