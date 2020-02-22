import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        long n, sum1, sum2;
        for (long i = 0; i < num; i++) {
            n = sc.nextLong();
            sum1 = n * (n + 1) / 2;
            sum2 = sum1 * (2 * n + 1) / 3;
            System.out.println((sum1 + sum2) / 2);
        }
    }
}

