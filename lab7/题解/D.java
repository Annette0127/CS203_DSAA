import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        long time = 0;
        int n = t / 100;
        int k = sc.nextInt();
        long s = sc.nextInt();
        int length = 0;
        long lastAns = s;
        long[] heap = new long[k + 1];
        for (int i = 0; i < (n * 100 + 1); i++) {
            time++;
            long ans = A(lastAns, time);
            if (length < k) {
                length++;
                insert(heap, length, ans);
            } else {
                if (ans > heap[1]) {delete(heap, length);
                    insert(heap, length, ans);
                }
            }
            if (time % 100 == 0) {
                System.out.print(heap[1] + " ");
                lastAns = heap[1];
            }
        }
        System.out.println();
    }

    public static void swap(long[] heap, int node1, int node2) {
        long temp = heap[node1];
        heap[node1] = heap[node2];
        heap[node2] = temp;
    }

    public static void insert(long[] nodes, int length, long element) {//remember length++ before
        nodes[length] = element;
        int pointer = length;
        while (pointer / 2 > 0 && nodes[pointer] < nodes[pointer / 2]) {
            swap(nodes, pointer, pointer / 2);
            pointer /= pointer;
        }
    }

    public static void delete(long[] nodes, int length) {//remember length--
        int pointer = 1;
        nodes[1] = nodes[length--];
        while ((pointer * 2 <= length && nodes[pointer] > nodes[pointer * 2])
                || ((pointer * 2 + 1) <= length && nodes[pointer] > nodes[pointer * 2 + 1])) {
            if (nodes[pointer * 2] <= nodes[pointer * 2 + 1]) {
                swap(nodes, pointer * 2, pointer);
                pointer *= 2;
            } else {
                swap(nodes, pointer * 2 + 1, pointer);
                pointer = pointer * 2 + 1;
            }
        }
    }

    public static long addDigit(long a) {
        long sum = 0;
        while (a != 0) {
            sum += (a - (a / 10) * 10);
            a = a / 10;
        }
        return sum;
    }

    public static long A(long a, long time) {
        return (time + a) + addDigit(time + a);
    }
}