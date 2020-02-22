import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (sc.hasNextLong()) {
                number(sc.nextLong() - 1);
            } else {
                cordinate(sc.next());
            }
        }
    }

    public static void cordinate(String s) {
        String[] corS = s.split("[(,)]+");
        long x = Long.parseLong(corS[1]) - 1;
        long y = Long.parseLong(corS[2]) - 1;
        long n = 1;
        for (long i = 0; x > 0 || y > 0; x >>= 1, y >>= 1, i += 2) {
            n += (((x % 2) ^ (y % 2)) << i) + ((y % 2) << (i + 1));
        }
        System.out.println(n);
    }

    public static void number(long n) {
        long x = 1;
        long y = 1;
        for (int i = 0; n > 0; n >>= 2, i++) {
            x += ((n + 1) % 4 / 2) << i;
            y += (n % 4 / 2) << i;
        }
        System.out.println("(" + x + "," + y + ")");
    }

}
