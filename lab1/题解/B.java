import java.util.Scanner;
import static java.util.Arrays.binarySearch;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int day = sc.nextInt();
        long[] money = new long[day];
        int f = sc.nextInt();
        long[] fish = new long[f];
        for (int i = 0; i < day; i++) {
            money[i] = sc.nextLong();
        }
        for (int i = 0; i < f; i++) {
            fish[i] = sc.nextLong();
        }
        for (int i = 0; i < day; i++) {
            if (money[i] < fish[0]) System.out.println(money[i]);
            else if (money[i] > fish[f - 1]) {
                System.out.println(money[i] - fish[f - 1]);
            } else {
                int res = binarySearch(fish, money[i]);
                if (res < 0) {
                    System.out.println(money[i]-fish[-res - 2]);
                } else System.out.println("Meow");
            }
        }
    }
}