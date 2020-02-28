import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long targ;
        int n;
        int cases = sc.nextInt();
        long note;
        for (int i = 0; i < cases; i++) {
            ListD loop = new ListD();
            n = sc.nextInt();
            targ = sc.nextLong();
            for (int j = 1; j <= n; j++) {
                note = sc.nextInt();
                loop.add(j, note);
            }
            loop.makeLoop();
            ListD.Item pointer = loop.getHead();
            while (true) {
                if (loop.size == 2) {
                    targ = targ % 2;
                    if (targ == 0) {
                        System.out.println(pointer.number);
                        break;
                    } else {
                        System.out.println(pointer.next.number);
                        break;
                    }
                } else {
                    targ = targ % loop.size;
                    if (loop.size / 2 > targ) {
                        if (targ == 0) pointer = pointer.before;
                        else {
                            for (int k = 1; k < targ; k++) {
                                pointer = pointer.next;
                            }
                        }
                    } else {
                        for (int j = 0; j < loop.size - targ + 1; j++) {
                            pointer = pointer.before;
                        }

                    }
                    pointer.before.next = pointer.next;
                    pointer.next.before = pointer.before;
                    targ = pointer.note;
                    pointer = pointer.next;
                    loop.size--;
                }
            }
        }
    }
}

class ListD {
    public long size;
    private Item head;
    private Item tail;

    public class Item {
        long number;
        long note;
        Item next;
        Item before;

        public Item(long num, long no) {
            number = num;
            note = no;
        }
    }

    public ListD.Item getHead() {
        return head;
    }

    public void add(long num, long no) {
        Item item = new Item(num, no);
        if (size == 0) {
            head = item;
            tail = item;
        } else {
            item.before = tail;
            tail.next = item;
            tail = item;
        }
        size++;
    }

    public void makeLoop() {
        tail.next = head;
        head.before = tail;
    }
}