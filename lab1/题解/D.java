import java.util.ArrayList;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long rep = 0;
        long count0 = 0;
        ArrayList<Long> ar = new ArrayList<>(n);
        ArrayList<Long> re = new ArrayList<>();
        long m = sc.nextLong();
        int mark = 0;//where is the border of positive and negative. First positive
        ar.add(sc.nextLong());
        mark = -1;
        long temp;
        if (ar.get(0) == 0) count0++;
        if (ar.get(0) > 0) mark = 0;
        for (int i = 1; i < n; i++) {
            temp = sc.nextLong();
            if (temp == 0) count0++;
            if (ar.get(ar.size() - 1) < temp) {
                ar.add(temp);
                if (ar.size() >= 2 && ar.get(ar.size() - 1) > 0 && ar.get(ar.size() - 2) <= 0) {
                    mark = ar.size() - 1;
                }
            } else {
                if (temp != 0) {
                    rep++;
                    if (re.size() == 0) re.add(temp);
                    if (re.get(re.size() - 1) != temp) {
                        re.add(temp);
                    }
                }
            }
        }
        if (mark == -1) mark = ar.size();
        int left, right, count = 0;
        if (m == 0) {
            if (count0 == 0) System.out.println(0);
            else if (count0 == 1) System.out.println(n - rep - count0);
            else System.out.println(n - rep - count0 + 1);
        } else if (m > 0) {
            left = 0;
            if (count0 == 0) right = mark - 1;
            else right = mark - 2;
            for (; left < right; ) {
                if (ar.get(left) * ar.get(right) < m) right--;
                else if (ar.get(left) * ar.get(right) == m) {
                    count++;
                    right--;
                } else {
                    left++;
                }
            }
            left = mark;
            right = ar.size() - 1;
            for (; left < right && mark < ar.size(); ) {
                if (ar.get(left) * ar.get(right) < m) left++;
                else if (ar.get(left) * ar.get(right) == m) {
                    count++;
                    left++;
                } else {
                    right--;
                }
            }
            for (int i = 0; i < re.size(); i++) {
                if ((re.get(i) * re.get(i)) == m) count++;
            }
            System.out.println(count);
        } else {
            if (count0 == 0) left = mark - 1;
            else left = mark - 2;
            right = ar.size() - 1;
            for (; left < right && left >= 0; ) {
                if (ar.get(left) * ar.get(right) < m) right--;
                else if (ar.get(left) * ar.get(right) == m) {
                    count++;
                    left--;
                } else {
                    left--;
                }
            }
            System.out.println(count);
        }
    }
}