import java.util.ArrayList;
import java.util.Scanner;

public class B {
    static int[][] input = new int[9][9];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> cor = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; ) {
                if (sc.hasNextInt()) {
                    input[i][j] = sc.nextInt();
                    j++;
                } else {
                    if (sc.next().equals("x")) {
                        input[i][j] = 45;
                        cor.add(i);
                        cor.add(j);
                        j++;
                    }
                }
            }
        }
        int a = cor.size();
        for (int i = 0; i < a; i += 2) {
            for (int j = 0; j < cor.size() - 1; ) {
                if (isOKC(cor.get(j), cor.get(j + 1))) {
                    cor.remove(j);
                    cor.remove(j);
                } else if (isOKL(cor.get(j), cor.get(j + 1))) {
                    cor.remove(j);
                    cor.remove(j);
                } else if (isOKS(cor.get(j), cor.get(j + 1))) {
                    cor.remove(j);
                    cor.remove(j);
                }
                j += 2;
            }
        }
        if (cor.size() == 0) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j += 3) {
                    System.out.print(input[i][j] + " " + input[i][j + 1] + " " + input[i][j + 2] + " |");
                    if (j == 0 || j == 3) System.out.print(" ");
                    else if (j == 6) System.out.println();
                }
                if (i == 2 || i == 5) System.out.println();
            }
        } else System.out.println("The test data is incorrect!");
    }

    public static boolean isOKS(int x, int y) {
        int x1 = x/3*3;
        int y1 = y/3*3;
        for (int i =x1; i < x1+3; i++) {
            for (int j = y1; j < y1+3; j++) {
                if (input[i][j] == 45) {
                    if (i != x || j != y) return false;
                }
            }
        }
        for (int i = x1; i < x1 + 3; i++) {
            for (int j = y1; j < y1 + 3; j++) {
                if (i == x && j == y) continue;
                input[x][y] -= input[i][j];
            }
        }
        return true;
    }


    public static boolean isOKL(int x, int y) {
        for (int j = 0; j < 9; j++) {
            if (input[x][j] == 45) {
                if (j != y) return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (i == y) continue;
            input[x][y] -= input[x][i];
        }
        return true;
    }

    public static boolean isOKC(int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (input[i][y] == 45) {
                if (i != x) return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (i == x) continue;
            input[x][y] -= input[i][y];
        }
        return true;
    }
}
