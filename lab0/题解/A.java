import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            sc.nextLine();
            sc.next();
            ans[i] = sc.next();
            sc.nextLine();
            sc.nextLine();
            if(i!=n-1)sc.nextLine();
        }
        for (String s:ans) {
            System.out.println(s);
        }
    }
}

