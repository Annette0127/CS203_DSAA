import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < cases; i++) {
            String s = sc.next();
            int[] next = compute_prefix_function(s);
            int min = s.length() - next[s.length() - 1];
            if (min == s.length()) System.out.println(s.length());
            else if(s.length() % min != 0) System.out.println(min - s.length() % min);
            else System.out.println(0);
        }
    }

    public static int[] compute_prefix_function(String p) {
        int m = p.length();
        int[] index = new int[m];
        index[0] = 0;
        for (int q = 1, k = 0; q < p.length(); q++) {
            while (k > 0 && p.charAt(k) != p.charAt(q)) k = index[k - 1];
            if (p.charAt(k) == p.charAt(q)) k++;
            index[q] = k;
        }
        return index;
    }
}