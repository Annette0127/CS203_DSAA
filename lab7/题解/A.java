import java.util.Scanner;


public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int node = sc.nextInt();
            int[][] nodes = new int[node + 1][3];
            for (int j = 1; j <= node; j++) {
                nodes[j][0] = -1;
            }
            int x, y;
            for (int j = 1; j <= node; j++) {
                x = sc.nextInt();
                y = sc.nextInt();
                nodes[j][1] = x;
                nodes[j][2] = y;
                nodes[x][0] = j;
                nodes[y][0] = j;
            }
            int root = 0;
            for (int j = 0; j <= node; j++) {
                if (nodes[j][0] == -1) {
                    root = j;
                    break;
                }
            }
            int[] queue = new int[2 * node + 2];
            int start = 1, end = 1;
            queue[start] = root;
            for (int j = 1; j < nodes.length; j++) {
                if (queue[start] != 0) {
                    queue[end + 1] = nodes[queue[start]][1];
                    end++;
                    queue[end + 1] = nodes[queue[start]][2];
                    end++;
                }
                start++;
            }
            int k = 1;
            while (true) {
                if (queue[k] == 0) break;
                k++;
            }


            if (node == k-1) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}
