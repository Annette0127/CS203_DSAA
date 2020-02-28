import java.util.Arrays;
import java.util.Scanner;


public class E {
    //    public static final long base = 113837;
    public static final long prime = 131;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int upperbound = Math.min(s1.length(), s2.length());
        int lowerbound = 0;
        int mid;
        boolean midok;
        int length = 0;
        while (lowerbound <= upperbound) {
            midok = false;
            mid = lowerbound + (upperbound - lowerbound) / 2;
            long[] n1 = hash1(s1, mid);
            long[] n2 = hash1(s2, mid);
            Arrays.sort(n1);
            for (int i = 0; i < n2.length; i++) {
                if (Arrays.binarySearch(n1, n2[i]) >= 0) {
                    midok = true;
//                    System.out.println(mid);
                    length = mid;
                    break;
                }
            }
            if (midok) {
                lowerbound = mid + 1;
            } else {
                if (upperbound == mid && mid == lowerbound) break;
                upperbound = mid;
            }
        }
        System.out.println(length);

    }

    public static long hash0(String s) {
        long x = 1;
        long result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            result = ((s.charAt(i) * x) + result);
            x = prime * x;
        }
        return result;
    }

    public static long[] hash1(String s, int length) {
        long[] hashvalue = new long[s.length() - length + 1];
        hashvalue[0] = hash0(s.substring(0, length));
        long m = 1;
        for (int i = 0; i < length - 1; i++) {
            m = (m * prime);
        }
        for (int i = 1, j = length; j < s.length(); i++, j++) {
            hashvalue[i] = (((hashvalue[i - 1] - (m * s.charAt(i - 1))) * prime) + s.charAt(j));
//            System.out.println(hashvalue[i] + " " + s.substring(i, i + length));
        }
        return hashvalue;
    }
}
