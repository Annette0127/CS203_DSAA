import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int n, p;
        boolean find;
        for (int k = 0; k < number; k++) {
            n = sc.nextInt();
            p = (int) Math.sqrt(2 * n);
            find = false;
            for (int i = 2; i <= p; i++) {
                if ((2 * n) % i == 0) {
                    if ((2 * n / i - i + 1) % 2 == 0) {
                        find = true;
                        p = i;
                    }
                }
            }
            if (find) {
                System.out.println(p);
            } else {
                System.out.println("impossible");
            }
        }
    }

}
