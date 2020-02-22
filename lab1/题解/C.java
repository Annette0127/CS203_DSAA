import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        if (a >= 4) System.out.println(0);
        else if (a == 1 || a == 2) {
            System.out.println(a % b);
        } else if (a == 0) {
            System.out.println(1%b);
        } else {
            if (b <= 720) System.out.println(0);
            else {
                long ans = (720 * 719) % b;
                for (int i = 718; i > 0; i--) {
                    ans = (ans * i) % b;
                    if (ans == 0) break;
                }
                System.out.println(ans);
            }
        }
    }
}