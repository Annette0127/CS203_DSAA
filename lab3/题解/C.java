import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            ListC inStr = new ListC();
            ListC.Item pointer = inStr.getHead();
            int n = sc.nextInt();
            String input = sc.next();
//            String input = generateLine(100);
            for (int j = 0; j < n; j++) {
                char operation = input.charAt(j);
                if (operation == 'r') {
                    j++;
                    if (j >= input.length()) break;
                    operation = input.charAt(j);
                    pointer = inStr.functionR(pointer, operation);
                } else if (operation == 'I') {
                    pointer = inStr.functionI(pointer);
                } else if (operation == 'H') {
                    pointer = inStr.functionH(pointer);
                } else if (operation == 'L') {
                    pointer = inStr.functionL(pointer);
                } else if (operation == 'x') {
                    pointer = inStr.functionX(pointer);
                } else {
                    pointer = inStr.addBefore(pointer, operation);
                }
            }
            pointer = inStr.getHead();
            if (pointer != inStr.getTail()) {
                while (pointer != inStr.getTail()) {
                    System.out.print(pointer.words);
                    pointer = pointer.next;
                }
            }
            System.out.println();
        }
    }

    //
//    public static String generateLine(int n) {
//        String result = "";
//        Random random = new Random();
//        for (int i = 0; i < n; i++) {
//            int num = random.nextInt(15);
//            String s = "";
//            switch (num) {
//                case 10:
//                    s = "r";
//                    break;
//                case 11:
//                    s = "x";
//                    break;
//                case 12:
//                    s = "H";
//                    break;
//                case 13:
//                    s = "L";
//                    break;
//                case 14:
//                    s = "I";
//                    break;
//                default:
//                    s = num + "";
//            }
//            result += s;
//        }
//        return result;
//    }
//
}


class ListC {
    long size;
    private Item head;
    private Item tail;

    public ListC() {
        tail = new Item('E');
        head = tail;
    }

    public class Item {
        char words;
        Item next;
        Item before;

        public Item(char w) {
            words = w;
        }
    }

    public ListC.Item getHead() {
        return head;
    }

    public ListC.Item getTail() {
        return tail;
    }

    public Item addBefore(Item after, char w) {
        Item item = new Item(w);
        if (size == 0) {
            head = item;
            item.next = tail;
            tail.before = item;
        } else if (after == head) {
            head = item;
            item.next = after;
            after.before = item;
        } else {
            item.next = after;
            item.before = after.before;
            item.before.next = item;
            item.next.before = item;
        }
        size++;
        return after;
    }

    public Item functionR(Item pointer, char in) {
        if (pointer != tail) {
            pointer.words = in;
            return pointer;
        } else {
            addBefore(tail, in);
            pointer = pointer.before;
            return pointer;
        }
    }

    public Item functionI(Item pointer) {
        pointer = head;
        return pointer;
    }

    public Item functionH(Item pointer) {
        if (pointer != head) return pointer.before;
        else return pointer;

    }

    public Item functionL(Item pointer) {
        if (pointer != tail) pointer = pointer.next;
        return pointer;
    }

    public Item functionX(Item pointer) {
        if (pointer == tail.before && pointer == head) {
            head.next = tail;
            head = tail;
            return tail;
        } else if (pointer == tail.before) {
            pointer.before.next = tail;
            tail.before = pointer.before;
            pointer = tail;
        } else if (pointer == head && pointer.next != null) {
            pointer.next.before = null;
            head = pointer.next;
            pointer = head;
        } else if (pointer == tail && pointer.before != null) {
            return tail;
        } else if (tail == head) {
            return pointer;
        } else {
            pointer.next.before = pointer.before;
            pointer.before.next = pointer.next;
            pointer = pointer.next;
        }
        size--;
        return pointer;
    }
}
