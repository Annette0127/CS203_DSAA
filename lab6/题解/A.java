import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        long n, k, need, have;
        for (int i = 0; i < cases; i++) {
            n = sc.nextLong();
            k = sc.nextLong();
            long h = 0;
            long kH = 1;
            while (kH < (k - 1) * n + 1) {
                h++;
                kH *= k;
            }
            need = (kH - 1) / (k - 1) - n;
            have = kH/k - need;
            System.out.println(need / k + have);
        }
    }
}
