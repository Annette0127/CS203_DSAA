import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int node, length;
        long coast;
        int pointer;
        for (int i = 0; i < cases; i++) {
            coast = 0;
            length = 0;
            node = sc.nextInt();
            long[] nodes = new long[node * 2 + 2];
            for (int j = 0; j < node; j++) {
                length++;
                nodes[length] = sc.nextLong();
                pointer = length;
                long temp;
                while (pointer / 2 > 0 && nodes[pointer / 2] > nodes[pointer]) {
                    swap(nodes, pointer / 2, pointer);
                    pointer /= 2;
                }
            }
            long x, y, sum;
            for (int j = 1; j < node; j++) {
                x = nodes[1];
                nodes[1] = nodes[length];
                pointer = 1;
                long temp;
                length--;
                while ((pointer * 2 <= length && nodes[pointer] > nodes[pointer * 2]) || (pointer * 2 + 1 <= length && nodes[pointer] > nodes[pointer * 2 + 1])) {
                    if (nodes[pointer * 2] <= nodes[pointer * 2 + 1]) {
                        swap(nodes, pointer * 2, pointer);
                        pointer *= 2;
                    } else {
                        swap(nodes, pointer * 2 + 1, pointer);
                        pointer = pointer * 2 + 1;
                    }

                }
                y = nodes[1];
                nodes[1] = nodes[length];
                pointer = 1;
                length--;
                while ((pointer * 2 <= length && nodes[pointer] > nodes[pointer * 2]) || (pointer * 2 + 1 <= length && nodes[pointer] > nodes[pointer * 2 + 1])) {
                    if (nodes[pointer * 2] <= nodes[pointer * 2 + 1]) {
                        temp = nodes[pointer];
                        nodes[pointer] = nodes[pointer * 2];
                        nodes[pointer * 2] = temp;
                        pointer *= 2;
                    } else {
                        temp = nodes[pointer];
                        nodes[pointer] = nodes[pointer * 2 + 1];
                        nodes[pointer * 2 + 1] = temp;
                        pointer = pointer * 2 + 1;
                    }
                }
                sum = x + y;
                coast += sum;
                length++;
                nodes[length] = sum;
                pointer = length;
                while (pointer / 2 > 0 && nodes[pointer / 2] > nodes[pointer]) {
                    temp = nodes[pointer / 2];
                    nodes[pointer / 2] = nodes[pointer];
                    nodes[pointer] = temp;
                    pointer /= 2;
                }
            }
            coast += nodes[0];
            System.out.println(coast);

        }
    }

    public static void swap(long[] arr, int node1, int node2) {
        long temp = arr[node1];
        arr[node1] = arr[node2];
        arr[node2] = temp;
    }
}