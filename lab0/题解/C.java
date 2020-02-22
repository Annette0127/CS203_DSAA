import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String temp;
        for (int i = 0; i < n; i++) {
            int[] w = new int[10];
            int[] t = new int[10];
            int[] y = new int[10];
            int[] q = new int[7];
            temp = sc.nextLine();
            String[] tempstr = temp.split("\\s");
            for (int j = 0; j < tempstr.length; j++) {
                if (tempstr[j].contains("W") && tempstr[j].length() == 2) {
                    w[Integer.parseInt(tempstr[j].substring(1, 2))]++;
                } else if (tempstr[j].contains("T")) {
                    t[Integer.parseInt(tempstr[j].substring(1, 2))]++;
                } else if (tempstr[j].contains("Y")) {
                    y[Integer.parseInt(tempstr[j].substring(1, 2))]++;
                } else if (tempstr[j].equals("E")) q[0]++;
                else if (tempstr[j].equals("S")) q[1]++;
                else if (tempstr[j].equals("W")) q[2]++;
                else if (tempstr[j].equals("N")) q[3]++;
                else if (tempstr[j].equals("B")) q[4]++;
                else if (tempstr[j].equals("F")) q[5]++;
                else if (tempstr[j].equals("Z")) q[6]++;
            }
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < w[j]; k++) {
                    System.out.print("W" + j + " ");
                }
            }
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < t[j]; k++) {
                    System.out.print("T" + j + " ");
                }
            }
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < y[j]; k++) {
                    System.out.print("Y" + j + " ");
                }
            }
            for (int j = 0; j < q[0]; j++) {
                System.out.print("E ");
            }
            for (int j = 0; j < q[1]; j++) {
                System.out.print("S ");
            }
            for (int j = 0; j < q[2]; j++) {
                System.out.print("W ");
            }
            for (int j = 0; j < q[3]; j++) {
                System.out.print("N ");
            }
            for (int j = 0; j < q[4]; j++) {
                System.out.print("B ");
            }
            for (int j = 0; j < q[5]; j++) {
                System.out.print("F ");
            }
            for (int j = 0; j < q[6]; j++) {
                System.out.print("Z ");
            }
            System.out.println();
        }
    }
}
