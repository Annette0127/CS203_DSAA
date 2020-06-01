import java.util.Scanner;
public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int nodes;
        for (int i = 0; i < cases; i++) {
            nodes = sc.nextInt();
            int[] pre_order = new int[nodes];
            int[] in_order = new int[nodes];
            for (int j = 0; j < nodes; j++) {
                pre_order[j] = sc.nextInt();
            }
            for (int j = 0; j < nodes; j++) {
                in_order[j] = sc.nextInt();
            }
            print(pre_order, in_order, 0, pre_order.length - 1, 0, in_order.length - 1);
            System.out.println();

        }
    }

    public static void print(int[] pre_order, int[] in_order, int pre_start, int pre_end,
                             int in_start, int in_end) {
        if (in_start > in_end) return;
        else {
            int mid = -1;
            for (int i = in_start; i <= in_end; i++) {
                if (pre_order[pre_start] == in_order[i]) {
                    mid = i;
                    break;
                }
            }
            print(pre_order, in_order, pre_start + 1, pre_start + mid - in_start, in_start, mid - 1);//left tree
            print(pre_order, in_order, pre_start + mid - in_start + 1, pre_end, mid + 1, in_end);//right
            System.out.print(in_order[mid] + " ");
        }
    }
}