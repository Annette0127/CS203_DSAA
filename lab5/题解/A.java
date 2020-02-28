import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < T; i++) {
            String a = sc.nextLine();
            int out = a.length() * (a.length() + 1) / 2;
            System.out.println(out);
        }

    }
}