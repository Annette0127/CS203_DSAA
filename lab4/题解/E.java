import java.io.*;

class SQe {
    Item tail;
    Item head;
    long size;


    public SQe() {
        tail = null;
        head = null;
    }

    class Item {
        long element;
        Item before;
        Item next;
        int index;

        public Item(long e, int in) {
            element = e;
            index = in;
        }
    }

    public void push(long e, int in) {
        Item item = new Item(e, in);
        size++;
        if (size == 1) {
            head = item;
            tail = item;
        } else {
            tail.next = item;
            item.before = tail;
            tail = item;
        }
    }

    public Item pop() {
        if (size != 0) {
            size--;
            Item a = tail;
            tail = tail.before;
            return a;
        } else {
            return null;
        }
    }

    public long dequeue() {
        if (size != 0) {
            size--;
            long a = head.element;
            head.before = null;
            head = head.next;
            return a;
        } else {
            return -1;
        }
    }
}

public class E{

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = in.nextInt();
            int[] people = new int[n + 1];
            int[][] partner = new int[n + 1][2];
            for (int j = 1; j <= n; j++) {
                people[j] = in.nextInt();
            }
            SQe SQe = new SQe();
            SQe.Item pointer, pointerP;
            for (int j = 1; j <= n; j++) {
                if (SQe.size != 0 && SQe.tail.element < people[j]) {
                    pointerP = SQe.pop();
                    partner[pointerP.index][0] = 0;
                    while (SQe.size != 0 && SQe.tail.element < people[j]) {
                        pointer = SQe.pop();
                        partner[pointer.index][0] = pointerP.index;
                        pointerP = pointer;
                    }
                }
                SQe.push(people[j], j);
            }
            pointerP = SQe.pop();
            partner[pointerP.index][0] = 0;
            while (SQe.size != 0) {
                pointer = SQe.pop();
                partner[pointer.index][0] = pointerP.index;
                pointerP = pointer;
            }

            for (int j = n; j >= 1; j--) {
                if (SQe.size != 0 && SQe.tail.element < people[j]) {
                    pointerP = SQe.pop();
                    partner[pointerP.index][1] = 0;
                    while (SQe.size != 0 && SQe.tail.element < people[j]) {
                        pointer = SQe.pop();
                        partner[pointer.index][1] = pointerP.index;
                        pointerP = pointer;
                    }
                }
                SQe.push(people[j], j);
            }
            pointerP = SQe.pop();
            partner[pointerP.index][1] = 0;
            while (SQe.size != 0) {
                pointer = SQe.pop();
                partner[pointer.index][1] = pointerP.index;
                pointerP = pointer;
            }

            out.println("Case " + (i+1) + ":");
            for (int j = 1; j <= n; j++) {
                out.println(partner[j][1]+" "+partner[j][0]);
            }
        }


        out.close();
    }


    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}