import java.util.Scanner;

class Stack<T> {
    Item top;
    Item bottom;
    int size;

    public Stack() {
        top = null;
        bottom = null;
    }

    class Item {
        T element;
        Item before;
        Item next;

        public Item(T e) {
            element = e;
        }
    }

    public void push(T e) {
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

    public T pop() {
        if (size != 0) {
            size--;
            T a = top.element;
            top = top.before;
            return a;
        } else {
            return null;
        }
    }
}

class StackL {
    Item top;
    Item bottom;
    int size;

    public StackL() {
        top = null;
        bottom = null;
    }

    class Item {
        int element;
        Item before;
        Item next;

        public Item(int e) {
            element = e;
        }
    }

    public void push(int e) {
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

    public int pop() {
        if (size != 0) {
            size--;
            int a = top.element;
            top = top.before;
            return a;
        } else {
            return 0;
        }
    }
}


public class G {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            String s = sc.next();
            char[] input = s.toCharArray();
            Stack<Character> op = new Stack<>();
            StackL num = new StackL();
            char[] sN = new char[s.length()];
            int pointer = 0;

            for (int j = 0; j < input.length; j++) {
                if (47 < input[j] && input[j] < 58) {
                    sN[pointer] = input[j];
                    pointer++;
                } else {
                    if (input[j] == '(') op.push('(');
                    else if (input[j] == ')') {
                        while (op.top.element != '(') {
                            sN[pointer] = op.pop();
                            pointer++;
                        }
                        op.pop();
                    } else {
                        if ((input[j] == '-') && (j == 0 || (!(input[j - 1] == 41 || (47 < input[j - 1] && input[j - 1] < 58))))) {
                            input[j] = 'f';
                        } else if ((input[j] == '+') && (j == 0 || (!(input[j - 1] == 41 || (47 < input[j - 1] && input[j - 1] < 58))))) {
                            continue;
                        }
                        while (op.top != null && compare(op.top.element, input[j]) == 1) {
                            sN[pointer] = op.pop();
                            pointer++;
                        }
                        if (op.top != null && compare(op.top.element, input[j]) == 0) {
                            if (input[j] != 'f' && input[j] != '~') {
                                sN[pointer] = op.pop();
                                pointer++;
                            }
                        }
                        op.push(input[j]);
                    }

                }
            }
            while (op.size != 0) {
                sN[pointer] = op.pop();
                pointer++;
            }
            int a, b;
            for (int j = 0; j < pointer; j++) {
                if (47 < sN[j] && sN[j] < 58) num.push(sN[j] - '0');
                else {
                    if (sN[j] == '~') {
                        a = num.pop();
                        num.push(~a);
                    } else if (sN[j] == 'f') {
                        a = num.pop();
                        num.push(-a);
                    } else if (sN[j] == '+') {
                        a = num.pop();
                        b = num.pop();
                        num.push(a + b);
                    } else if (sN[j] == '-') {
                        a = num.pop();
                        b = num.pop();
                        num.push(b - a);
                    } else if (sN[j] == '*') {
                        a = num.pop();
                        b = num.pop();
                        num.push(a * b);
                    } else if (sN[j] == '^') {
                        a = num.pop();
                        b = num.pop();
                        num.push(a ^ b);
                    } else if (sN[j] == '&') {
                        a = num.pop();
                        b = num.pop();
                        num.push(a & b);
                    } else if (sN[j] == '|') {
                        a = num.pop();
                        b = num.pop();
                        num.push(a | b);
                    }
                }
            }
            int sum = num.pop();
            System.out.println(sum);
        }
    }

    static int compare(char a, char b) {
        int a1, b1;
        if (a == 'f') a1 = 6;
        else if (a == '~') a1 = 6;
        else if (a == '*') a1 = 5;
        else if (a == '-' || a == '+') a1 = 4;
        else if (a == '&') a1 = 3;
        else if (a == '^') a1 = 2;
        else if (a == '|') a1 = 1;
        else a1 = 0;

        if (b == 'f') b1 = 6;
        else if (b == '~') b1 = 6;
        else if (b == '*') b1 = 5;
        else if (b == '-' || b == '+') b1 = 4;
        else if (b == '&') b1 = 3;
        else if (b == '^') b1 = 2;
        else if (b == '|') b1 = 1;
        else b1 = 0;

        if (a1 > b1) return 1;
        else if (a1 == b1) return 0;
        else return -1;
    }
}

//***G***//
//import java.io.*;
//
//class Stack<T> {
//    Item top;
//    Item bottom;
//    int size;
//
//    public Stack() {
//        top = null;
//        bottom = null;
//    }
//
//    class Item {
//        T element;
//        Item before;
//        Item next;
//
//        public Item(T e) {
//            element = e;
//        }
//    }
//
//    public void push(T e) {
//        Item item = new Item(e);
//        size++;
//        if (top == null) {
//            top = item;
//            bottom = item;
//        } else {
//            top.next = item;
//            item.before = top;
//            top = item;
//        }
//    }
//
//    public T pop() {
//        if (size != 0) {
//            size--;
//            T a = top.element;
//            top = top.before;
//            return a;
//        } else {
//            return null;
//        }
//    }
//}
//
//class StackL {
//    Item top;
//    Item bottom;
//    int size;
//
//    public StackL() {
//        top = null;
//        bottom = null;
//    }
//
//    class Item {
//        int element;
//        Item before;
//        Item next;
//
//        public Item(int e) {
//            element = e;
//        }
//    }
//
//    public void push(int e) {
//        Item item = new Item(e);
//        size++;
//        if (top == null) {
//            top = item;
//            bottom = item;
//        } else {
//            top.next = item;
//            item.before = top;
//            top = item;
//        }
//    }
//
//    public int pop() {
//        if (size != 0) {
//            size--;
//            int a = top.element;
//            top = top.before;
//            return a;
//        } else {
//            return 0;
//        }
//    }
//}
//
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        Reader in = new Reader();
//        PrintWriter out = new PrintWriter(System.out);
//
//        int cases = in.nextInt();
//        for (int i = 0; i < cases; i++) {
//            String s = in.readLine();
//            char[] input = s.toCharArray();
//            Stack<Character> op = new Stack<>();
//            StackL num = new StackL();
//            char[] sN = new char[s.length()];
//            int pointer = 0;
//
//            for (int j = 0; j < input.length; j++) {
//                if (47 < input[j] && input[j] < 58) {
//                    sN[pointer] = input[j];
//                    pointer++;
//                } else {
//                    if (input[j] == '(') op.push('(');
//                    else if (input[j] == ')') {
//                        while (op.top.element != '(') {
//                            sN[pointer] = op.pop();
//                            pointer++;
//                        }
//                        op.pop();
//                    } else {
//                        if ((input[j] == '-') && (j == 0 || (!(input[j - 1] == 41 || (47 < input[j - 1] && input[j - 1] < 58))))) {
//                            input[j] = 'f';
//                        } else if ((input[j] == '+') && (j == 0 || (!(input[j - 1] == 41 || (47 < input[j - 1] && input[j - 1] < 58))))) {
//                            continue;
//                        }
//                        while (op.top != null && compare(op.top.element, input[j]) == 1) {
//                            sN[pointer] = op.pop();
//                            pointer++;
//                        }
//                        if (op.top != null && compare(op.top.element, input[j]) == 0) {
//                            if (input[j] != 'f' && input[j] != '~') {
//                                sN[pointer] = op.pop();
//                                pointer++;
//                            }
//                        }
//                        op.push(input[j]);
//                    }
//
//                }
//            }
//            while (op.size != 0) {
//                sN[pointer] = op.pop();
//                pointer++;
//            }
//            int a, b;
//            for (int j = 0; j < pointer; j++) {
//                if (47 < sN[j] && sN[j] < 58) num.push(sN[j] - '0');
//                else {
//                    if (sN[j] == '~') {
//                        a = num.pop();
//                        num.push(~a);
//                    } else if (sN[j] == 'f') {
//                        a = num.pop();
//                        num.push(-a);
//                    } else if (sN[j] == '+') {
//                        a = num.pop();
//                        b = num.pop();
//                        num.push(a + b);
//                    } else if (sN[j] == '-') {
//                        a = num.pop();
//                        b = num.pop();
//                        num.push(b - a);
//                    } else if (sN[j] == '*') {
//                        a = num.pop();
//                        b = num.pop();
//                        num.push(a * b);
//                    } else if (sN[j] == '^') {
//                        a = num.pop();
//                        b = num.pop();
//                        num.push(a ^ b);
//                    } else if (sN[j] == '&') {
//                        a = num.pop();
//                        b = num.pop();
//                        num.push(a & b);
//                    } else if (sN[j] == '|') {
//                        a = num.pop();
//                        b = num.pop();
//                        num.push(a | b);
//                    }
//                }
//            }
//            int sum = num.pop();
//            out.println(sum);
//        }
//        out.close();
//    }
//
//    static int compare(char a, char b) {
//        int a1, b1;
//        if (a == 'f') a1 = 6;
//        else if (a == '~') a1 = 6;
//        else if (a == '*') a1 = 5;
//        else if (a == '-' || a == '+') a1 = 4;
//        else if (a == '&') a1 = 3;
//        else if (a == '^') a1 = 2;
//        else if (a == '|') a1 = 1;
//        else a1 = 0;
//
//        if (b == 'f') b1 = 6;
//        else if (b == '~') b1 = 6;
//        else if (b == '*') b1 = 5;
//        else if (b == '-' || b == '+') b1 = 4;
//        else if (b == '&') b1 = 3;
//        else if (b == '^') b1 = 2;
//        else if (b == '|') b1 = 1;
//        else b1 = 0;
//
//        if (a1 > b1) return 1;
//        else if (a1 == b1) return 0;
//        else return -1;
//    }
//
//
//    static class Reader {
//        final private int BUFFER_SIZE = 1 << 16;
//        private DataInputStream din;
//        private byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public Reader() {
//            din = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public Reader(String file_name) throws IOException {
//            din = new DataInputStream(new FileInputStream(file_name));
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public String readLine() throws IOException {
//            byte[] buf = new byte[64]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1) {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }
//
//        public int nextInt() throws IOException {
//            int ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            } while ((c = read()) >= '0' && c <= '9');
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public long nextLong() throws IOException {
//            long ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public double nextDouble() throws IOException {
//            double ret = 0, div = 1;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//
//            if (c == '.') {
//                while ((c = read()) >= '0' && c <= '9') {
//                    ret += (c - '0') / (div *= 10);
//                }
//            }
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private void fillBuffer() throws IOException {
//            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        }
//
//        private byte read() throws IOException {
//            if (bufferPointer == bytesRead)
//                fillBuffer();
//            return buffer[bufferPointer++];
//        }
//
//        public void close() throws IOException {
//            if (din == null)
//                return;
//            din.close();
//        }
//    }
//
//}