import java.util.ArrayList;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int nodes, a, b;
        for (int i = 0; i < cases; i++) {
            nodes = sc.nextInt();
            ArrayList<Integer>[] relation = new ArrayList[nodes + 1];
            for (int j = 1; j <= nodes; j++) {
                relation[j] = new ArrayList<>();
            }
            for (int j = 1; j < nodes; j++) {
                a = sc.nextInt();
                b = sc.nextInt();
                relation[a].add(b);
                relation[b].add(a);
            }

            int[][] tree = new int[nodes + 1][2];
            tree[1][0] = 0;
            tree[1][1] = -1;
            tree = findLayer(tree, relation, 1, -1);
            for (int j = 1; j <= nodes; j++) {
                System.out.print(tree[j][0] + " ");
            }
            System.out.println();
        }
    }

    static int[][] findLayer(int[][] tree, ArrayList<Integer>[] relation, int father, int grandfather) {
        for (int i = 0; i < relation[father].size(); i++) {
            if (grandfather != relation[father].get(i)) {
                tree[relation[father].get(i)][0] = tree[father][0] + 1;
                tree[relation[father].get(i)][1] = father;
                tree = findLayer(tree, relation, relation[father].get(i), father);
            }
        }
        return tree;
    }
}
