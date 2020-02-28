import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int length;
        for (int i = 0; i < cases; i++) {
            boolean ok = true;
            StackB stack = new StackB();
            length = sc.nextInt();
            sc.nextLine();
            String input = sc.nextLine();
            for (int j = 0; j < length && ok; j++) {
                if (input.charAt(j) == '(' || input.charAt(j) == '{' || input.charAt(j) == '[') {
                    stack.push(input.charAt(j));
                } else {
                    if (input.charAt(j) == ')' || input.charAt(j) == ']' || input.charAt(j) == '}') {
                        if (stack.size == 0) {
                            ok = false;
                        } else {
                            if ((input.charAt(j) == ')' && stack.pop() != '(')
                                    || (input.charAt(j) == ']' && stack.pop() != '[')
                                    || (input.charAt(j) == '}' && stack.pop() != '{')) {
                                ok = false;
                            }
                        }
                    }
                }
            }
            if (ok&&stack.size==0) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}

class StackB {
    Item top;
    Item bottom;
    int size;

    public StackB() {
        top = null;
        bottom = null;
    }

    class Item {
        char element;
        Item before;
        Item next;

        public Item(char e) {
            element = e;
        }
    }

    public void push(char e) {
        Item item = new Item(e);
        size++;
        if (top == null) {
            top = item;
            bottom = item;
        } else {
            top.next = item;
            item.before = top;
            top = item;
        }
    }

    public char pop() {
        if (size != 0) {
            size--;
            char a = top.element;
            top = top.before;
            return a;
        } else {
            return ' ';
        }
    }
}