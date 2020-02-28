import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String ope;
        Queue1 queue = new Queue1();
        for (int i = 0; i < n; i++) {
            ope = sc.nextLine();
            if (ope.charAt(0) == 'E') {
                queue.enqueue(Integer.parseInt(ope.substring(2)));
            } else if (ope.charAt(0) == 'D') {
                if (!queue.isEmpty()) queue.dequeue();
            } else if (ope.charAt(0) == 'A') {
                if (!queue.isEmpty()) System.out.println(queue.head.element);
            }
        }
        Queue1.Item pointer = queue.head;
        for (int i = 0; i < queue.size; i++) {
            System.out.print(pointer.element + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }
}

class Queue1 {
    Item head;
    Item tail;
    int size;

    public Queue1() {
        head = null;
        tail = null;
    }

    class Item {
        int element;
        Item before;
        Item next;

        public Item(int e) {
            element = e;
        }
    }

    public void enqueue(int e) {
        Item item = new Item(e);
        size++;
        if (head == null) {
            head = item;
            tail = item;
        } else {
            tail.next = item;
            item.before = tail;
            tail = item;
        }
    }

    public int dequeue() {
        size--;
        int a = head.element;
        head = head.next;
        return a;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
