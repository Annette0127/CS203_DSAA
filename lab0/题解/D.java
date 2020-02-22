import java.util.Scanner;

public class D {
    static int count;
    static long n;
    static String ls;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();
        for (int z = 0; z < amount; z++) {
            n = sc.nextLong();
            ls = "" + n;
            for (int i = 0; i <= 9; i++) {
                odd("" + i + "");
            }
            for (int i = 0; i <= 9; i++) {
                even("" + i + i + "");
            }
            System.out.println(count);
            count=0;
        }


    }

    public static void odd(String s) {
        if (Long.parseLong(s) > n) {
            return;
        }
        if (s.length() > ls.length()) {
            return;
        } else {
            if (weiShu(s) == s.length() ) {
                count++;
            }
            for (int i = 0; i <= 9; i++) {
                odd(i + s + i);
            }
        }
    }

    public static void even(String s) {
        if (Long.parseLong(s) > n) {
            return;
        }
        if (s.length() > ls.length()) {
            return;
        } else {
            if (weiShu(s) == s.length() ) {
                count++;
            }
            for (int i = 0; i <= 9; i++) {
                odd(i + s + i);
            }
        }
    }

    public static int weiShu(String s) {
        long temp = Long.parseLong(s);
        String temps = "" + temp;
        return temps.length();
    }
}
