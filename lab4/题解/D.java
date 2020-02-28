import java.io.*;

class StcakD {
    Item top;
    Item bottom;
    int size;

    public StcakD() {
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
            return -1;
        }
    }
}

public class D {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int cases = in.nextInt();
        int n;
        StcakD StcakD = new StcakD();
        int pointerIn, pointerOut;
        int input;
        for (int i = 0; i < cases; i++) {
            n = in.nextInt();
            boolean[] inStcakD = new boolean[n + 1];
            int[] output = new int[n];
            pointerIn = 1;
            pointerOut = 0;
            for (int j = 0; j < n; j++) {
                input = in.nextInt();
                if (input == pointerIn) {
                    output[pointerOut] = input;
                    pointerOut++;
                    pointerIn++;
                    while (pointerIn <= n && inStcakD[pointerIn]) {
                        if (StcakD.size > 0 && StcakD.top.element <= pointerIn) output[pointerOut++] = StcakD.pop();
                        else pointerIn++;

                    }
                } else {
                    StcakD.push(input);
                    inStcakD[input] = true;
                }
            }
            while (StcakD.size != 0) {
                output[pointerOut] = StcakD.pop();
                pointerOut++;
            }
            for (int j = 0; j < n - 1; j++) {
                out.print(output[j] + " ");
            }
            out.println(output[n - 1]);
        }
//

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