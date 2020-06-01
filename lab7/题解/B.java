import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        boolean right;
        int dadBigger, dadSmaller;
        for (int i = 0; i < cases; i++) {
            dadBigger = 0;
            dadSmaller = 0;
            right = true;
            int node = sc.nextInt();
            int[][] nodes = new int[node + 1][4];
            for (int j = 1; j <= node; j++) {
                nodes[j][0] = sc.nextInt();
                nodes[j][1] = -1;
            }
            int x, y;
            for (int j = 1; j < node; j++) {
                x = sc.nextInt();
                y = sc.nextInt();
                nodes[y][1] = x;
                if (nodes[x][2] == 0) nodes[x][2] = y;
                else if (nodes[x][3] == 0) nodes[x][3] = y;
                else right = false;
                if (nodes[x][0] >= nodes[y][0]) dadBigger++;
                if (nodes[x][0] <= nodes[y][0]) dadSmaller++;
            }
            if (dadBigger != node - 1 && dadSmaller != node - 1) right = false;
            if (right) {
                int root = 0;
                for (int j = 0; j <= node; j++) {
                    if (nodes[j][1] == -1) {
                        root = j;
                        break;
                    }
                }
                int[] queue = new int[2 * node + 2];
                int start = 1, end = 1;
                queue[start] = root;
                for (int j = 1; j < nodes.length; j++) {
                    if (queue[start] != 0) {
                        queue[end + 1] = nodes[queue[start]][2];
                        end++;
                        queue[end + 1] = nodes[queue[start]][3];
                        end++;
                    }
                    start++;
                }
                int k = 1;
                while (true) {
                    if (queue[k] == 0) break;
                    k++;
                }
                if (node != k - 1) right = false;
            }
            if (right) System.out.println("Case #" + (i+1) + ": YES");
            else System.out.println("Case #" + (i+1) + ": NO");
        }
    }
}
