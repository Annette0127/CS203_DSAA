import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        String s, t;
        boolean tr;
        for (int i = 0; i < cases; i++) {
            tr = true;
            int sl = sc.nextInt();
            int tl = sc.nextInt();
            sc.nextLine();
            s = sc.nextLine();
            t = sc.nextLine();
            int sp,tp;
            for ( sp = 0, tp = 0; sp < s.length() && tp < t.length(); ) {
                if (s.charAt(sp) == t.charAt(tp)) {
                    sp++;
                    tp++;
                } else if (s.charAt(sp) == '*') {
                    tp += tl - sl + 1;
                    sp++;
                } else if (s.charAt(sp) != t.charAt(tp)) {
                    tr = false;
                    break;
                }
            }
            if(sp!=s.length()||tp!=t.length())tr = false;
            if (tr) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
