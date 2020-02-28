import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        long m, n;
        long quick, slow;
        long max;
        for (int i = 0; i < cases; i++) {
            n = sc.nextLong();
            m = sc.nextLong();
            slow = m;
            quick = takeSubStr(n, m*m);
            max = Math.max(slow,quick);
            while (quick != slow) {
                slow = takeSubStr(n, slow*slow);
                quick = takeSubStr(n, quick*quick);
                if (max < quick) max = quick;
                quick =takeSubStr(n,quick*quick);
                if (max < quick) max = quick;
            }
            System.out.println(max);
        }
    }

    public static long takeSubStr(long requireN, long n) {
        int nLength = count(n);
        for (int i = 0; i < nLength - requireN; i++) {
            n = n / 10;
        }
        return n;
    }

    public static int count(long n) {
        int count = 0;
        while (n > 0) {
            n /= 10;
            count++;
        }
        return count;
    }
}
