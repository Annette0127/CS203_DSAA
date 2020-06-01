import java.util.*;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int length;
        for (int i = 0; i < cases; i++) {
            length = sc.nextInt();
            ArrayList<Long> ar = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                ar.add(sc.nextLong());
            }
            Collections.sort(ar);
            long temp = 0, result = 0;
            for (int j = 0; j < length - 1; j++) {
                temp = ar.get(0) + ar.get(1);
                result += temp;
                ar.remove(1);
                ar.set(0, temp);
                Collections.sort(ar);
            }
            System.out.println(result);
        }
    }
}
