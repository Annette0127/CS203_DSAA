import java.util.ArrayList;
import java.util.Scanner;

public class F {
    public static int no, max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int nodes, a, b, people, c;
        for (int i = 0; i < cases; i++) {
            max = 0;
            no = 0;
            nodes = sc.nextInt();
            people = sc.nextInt();
            ArrayList<Integer>[] relation = new ArrayList[nodes + 1];
            for (int j = 0; j <= nodes; j++) {
                relation[j] = new ArrayList<>();
            }
            for (int j = 1; j < nodes; j++) {
                a = sc.nextInt();
                b = sc.nextInt();
                relation[a].add(b);
                relation[b].add(a);
            }
            int[][] tree = new int[nodes + 1][3];
            for (int j = 0; j < people; j++) {
                c = sc.nextInt();
                tree[c][2] = 1;
            }
            tree[1][0] = 0;
            tree[1][1] = -1;
            findLayer(tree, relation, 1, -1);
            max = 0;
            for (int j = 1; j <= nodes; j++) {
                tree[j][0] = 0;
                tree[j][1] = -1;
            }
            tree[no][0] = 0;
            tree[no][1] = -1;
            int length = findLayer(tree, relation, no, -1);
            length = (int) Math.ceil((double) length / 2);
            System.out.println(length);
        }

    }

    static int findLayer(int[][] tree, ArrayList<Integer>[] relation, int father, int grandfather) {
        int temp, temp1;
        for (int i = 0; i < relation[father].size(); i++) {
            if (grandfather != relation[father].get(i)) {
                temp1 = tree[relation[father].get(i)][0] = tree[father][0] + 1;
                tree[relation[father].get(i)][1] = father;
                temp = findLayer(tree, relation, relation[father].get(i), father);
                if (max < temp1 && tree[relation[father].get(i)][2] == 1) {
                    max = temp1;
                    no = relation[father].get(i);
                }
                if (max < temp) {
                    max = temp;
                }
            }
        }
        return max;
    }
}
