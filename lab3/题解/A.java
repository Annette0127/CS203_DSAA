import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int m, n;
        for (int i = 0; i < cases; i++) {
            Poly poly1 = new Poly();
            n = sc.nextInt();
            poly1.addAfter(null, sc.nextLong(), sc.nextLong());
            Poly.Item pointer = poly1.getHead();
            for (int j = 0; j < n - 1; j++) {
                pointer = poly1.addAfter(pointer, sc.nextLong(), sc.nextLong());
            }
            pointer = poly1.getHead();
            m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                pointer = poly1.add(pointer, sc.nextLong(), sc.nextLong());
            }
            pointer = poly1.getHead();
            long exp;
            long number = sc.nextLong();
            Poly.Item temp = pointer;
            for (int j = 0; j < number; j++) {
                exp = sc.nextLong();
                while (pointer.exponent < exp) {
                    if (pointer.next == null) break;
                    temp = pointer;
                    pointer = temp.next;
                }
                if (pointer.exponent == exp) System.out.print(pointer.coefficient + " ");
                else System.out.print(0 + " ");
            }
            System.out.println();
//            while (pointer != null) {
//                System.out.println(pointer.coefficient + " ^ " + pointer.exponent);
//                System.out.println(pointer.before + "         " + pointer.next);
//                pointer = pointer.next;
//            }

        }

    }
}


class Poly {
    long size;
    private Item head;

    public class Item {
        long coefficient;
        long exponent;
        Item next;
        Item before;

        public Item(long cof, long exp) {
            coefficient = cof;
            exponent = exp;
        }
    }

    public Poly.Item getHead() {
        return head;
    }

    public boolean hasNext(Item item) {
        if (item.next == null) return false;
        else return true;
    }

    public Item addAfter(Item before, long cof, long exp) {
        Item item = new Item(cof, exp);
        if (size == 0) {
            head = item;
        } else {
            before.next = item;
            item.before = before;
        }
        size++;
        return item;
    }

    public Item add(Item pointer, long cof, long exp) {
        Item after = pointer;
        while (exp >= after.exponent) {
            pointer = after;
            after = pointer.next;
            if (after == null) break;
        }
        if (exp < pointer.exponent) {
            Item item = new Item(cof, exp);
            if (pointer == head) {
                item.next = head;
                pointer.before = item;
                head = item;
            } else {
                item.next = pointer;
                item.before = pointer.before;
                pointer.before = item;
                item.before.next = item;
            }
            return item;
        } else if (exp == pointer.exponent) {
            pointer.coefficient = cof + pointer.coefficient;
            return pointer;
        } else {
            Item item = new Item(cof, exp);
            if (pointer.next == null) {
                pointer.next = item;
                item.before = pointer;
            } else {
                item.before = pointer;
                item.next = pointer.next;
                pointer.next = item;
                item.next.before = item;

            }
            return item;
        }
    }
}
