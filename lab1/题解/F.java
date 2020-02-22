import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        long res;
        for (long i = 0; i < num; i++) {
            res = 0;
            long a = sc.nextLong();
            for (; a != 0; ) {
                a = a / 5;
                res += a;
            }
            System.out.println(res);
        }
    }
}
