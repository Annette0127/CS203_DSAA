import java.util.Scanner;

public class B{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int m, n, t;
        for (int i = 0; i < cases; i++) {
            n = sc.nextInt();
            ListB.Item[] address = new ListB.Item[n];
            m = sc.nextInt();
            ListB team = new ListB();
            ListB.Item pointer = null;
            for (int j = 0; j < n; j++) {
                t = sc.nextInt();
                pointer = team.addAfter(pointer, t);
                address[t] = pointer;
            }
            int x1, x2, y1, y2;

            for (int j = 0; j < m; j++) {
                x1 = sc.nextInt();
                y1 = sc.nextInt();
                x2 = sc.nextInt();
                y2 = sc.nextInt();
                team.switches(address[x1], address[y1], address[x2], address[y2]);
            }
            pointer = team.getHead();
            while (pointer != null) {
                System.out.print(pointer.number + " ");
                pointer = pointer.next;
            }
            System.out.println();
        }
    }
}

class ListB {
    long size;
    private Item head;
    private Item tail;

    public class Item {
        long number;
        Item next;
        Item before;

        public Item(int num) {
            number = num;
        }
    }

    public ListB.Item getHead() {
        return head;
    }

    public ListB.Item getTail() {
        return tail;
    }

    public boolean hasNext(Item item) {
        if (item.next == null) return false;
        else return true;
    }

    public Item addAfter(Item before, int num) {
        Item item = new Item(num);
        if (size == 0) {
            head = item;
            tail = item;
        } else {
            before.next = item;
            item.before = before;
            tail = item;
        }
        size++;
        return item;
    }

    public void switches(Item x1, Item y1, Item x2, Item y2) {
        Item temp1 = new Item(-1);
        Item temp2 = new Item(-1);
        if (y1 == tail) tail = temp1;
        else {
            temp1.next = y1.next;
            y1.next.before = temp1;
        }

        if (x1 == head) head = temp1;
        else {
            temp1.before = x1.before;
            x1.before.next = temp1;
        }

        if (y2 == tail) tail = temp2;
        else {
            temp2.next = y2.next;
            y2.next.before = temp2;
        }
        if (x2 == head) head = temp2;
        else {
            temp2.before = x2.before;
            x2.before.next = temp2;
        }

        if (temp1 == tail) {
            tail = y2;
            y2.next = null;
        } else {
            y2.next = temp1.next;
            temp1.next.before = y2;
        }
        if (temp1 == head) {
            head = x2;
            x2.before = null;
        } else {
            x2.before = temp1.before;
            temp1.before.next = x2;
        }

        if (temp2 == tail) {
            tail = y1;
            y1.next = null;
        } else {
            y1.next = temp2.next;
            temp2.next.before = y1;
        }
        if (temp2 == head) {
            head = x1;
            x1.before = null;
        } else {
            x1.before = temp2.before;
            temp2.before.next = x1;
        }
    }
}
