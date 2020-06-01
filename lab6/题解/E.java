import java.util.ArrayList;
import java.util.Scanner;

public class E {
    static int value;

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
            int[][] tree = new int[nodes + 1][3];
            tree[1][0] = -1;
            tree = findFather(tree, relation, 1, -1);
            int color;
            for (int j = 1; j <= nodes; j++) {
                color = sc.nextInt();
                if (color == 1) {//red
                    addRed(tree, relation, j);
                } else if (color == 2) {//blue
                    addBlue(tree, relation, j);
                }
            }
            value = 0;
            findValue(tree, relation);
            System.out.println(value);
        }
    }

    static int[][] findFather(int[][] tree, ArrayList<Integer>[] relation, int father, int grandfather) {
        int markFather = 0;
        for (int i = 0; i < relation[father].size(); i++) {
            if (grandfather != relation[father].get(i)) {
                tree[relation[father].get(i)][0] = father;
                tree = findFather(tree, relation, relation[father].get(i), father);
            } else {
                markFather = i;
            }
        }
        if (father != 1) relation[father].remove(markFather);
        return tree;
    }

    static void addRed(int[][] tree, ArrayList<Integer>[] relation, int num) {
        if (num != -1) {
            tree[num][1]++;
            addRed(tree, relation, tree[num][0]);
        }
    }

    static void addBlue(int[][] tree, ArrayList<Integer>[] relation, int num) {
        if (num != -1) {
            tree[num][2]++;
            addBlue(tree, relation, tree[num][0]);
        }
    }

    static void findValue(int[][] tree, ArrayList<Integer>[] relation) {
        int red, blue, reds, blues;
        for (int i = 1; i < relation.length; i++) {
            for (int j = 0; j < relation[i].size(); j++) {
                if (tree[1][1] - tree[relation[i].get(j)][1] == 0 && tree[relation[i].get(j)][2] == 0) value++;
                else if (tree[1][2] - tree[relation[i].get(j)][2] == 0 && tree[relation[i].get(j)][1] == 0) value++;
            }
        }
    }
}