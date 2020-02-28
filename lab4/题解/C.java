import java.io.*;

public class C {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        long n = in.nextLong();
        long inp, result = 0;
        long[] input = new long[2000000];
        inp = in.nextLong();
        int pointer = -1;//size of array
        do {
            pointer++;
            input[pointer] = inp;
            inp = in.nextLong();
        } while (inp != -1);

        SQc SQc = new SQc();

        for (int i = 0; i <= pointer; i++) {
            while (SQc.size != 0 && SQc.tail != null && SQc.tail.element < input[i]) {
                SQc.pop();
            }
            while (SQc.size != 0 && SQc.head != null && i - SQc.head.index > n-1) {
                SQc.dequeue();
            }
            SQc.push(input[i], i);
            if (i >= n-1) {
                result = result ^ SQc.head.element;
            }
        }
        out.println(result);


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

class SQc {
    Item tail;
    Item head;
    long size;


    public SQc() {
        tail = null;
        head = null;
    }

    class Item {
        long element;
        Item before;
        Item next;
        long index;

        public Item(long e, long in) {
            element = e;
            index = in;
        }
    }

    public void push(long e, long in) {
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

    public long pop() {
        if (size != 0) {
            size--;
            long a = tail.element;
            tail = tail.before;
            return a;
        } else {
            return -1;
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