import java.io.*;

class SQ {
    Item tail;
    Item head;
    long size;


    public SQ() {
        tail = null;
        head = null;
    }

    class Item {
        int element;
        Item before;
        Item next;
        int index;

        public Item(int e, int in) {
            element = e;
            index = in;
        }
    }

    public void push(int e, int in) {
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

    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }
}

public class F {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int t = in.nextInt();
        for (int l = 0; l < t; l++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] input = new int[n][n];
            int[] max = new int[n];
            int[] min = new int[n];
            SQ maxsq = new SQ();
            SQ minsq = new SQ();
            int output = 0, ans;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    input[i][j] = in.nextInt();
                }
            }
            for (int i = 0; i < n; i++) {//upper bound
                for (int j = 0; j < n; j++) {
                    min[j] = 2 << 18;
                    max[j] = 0;
                }
                for (int j = i; j < n; j++) {//lower bound
//                    for (int k = 0; k < n; k++) {
//                          if (max[k] < input[j][k]) max[k] = input[j][k];
//                          if (min[k] > input[j][k]) min[k] = input[j][k];
//                    }
                    maxsq.clear();
                    minsq.clear();
                    int left = 0;
                    for (int k = 0; k < n; k++) {// k是右边界
                        if (max[k] < input[j][k]) max[k] = input[j][k];
                        if (min[k] > input[j][k]) min[k] = input[j][k];
                        while (maxsq.size != 0 && maxsq.tail.element < max[k]) {
                            maxsq.pop();
                        }
                        while (minsq.size != 0 && minsq.tail.element > min[k]) {
                            minsq.pop();
                        }
                        maxsq.push(max[k], k);
                        minsq.push(min[k], k);
                        while (maxsq.size != 0 && minsq.size != 0 && maxsq.head.element - minsq.head.element > m) {
                            if (maxsq.head.index < minsq.head.index) {
                                left = maxsq.head.index + 1;
                                maxsq.dequeue();
                            } else if (maxsq.head.index == minsq.head.index) {
                                left = minsq.head.index + 1;
                                maxsq.dequeue();
                                minsq.dequeue();
                            } else {
                                left = minsq.head.index + 1;
                                minsq.dequeue();
                            }
//                            if (maxsq.head.index < left) maxsq.dequeue();
//                            if (minsq.head.index < left) minsq.dequeue();
                        }
                        ans = (j - i + 1) * (k - left + 1);
                        output=Math.max(ans,output);

                    }
                }
            }
            out.println(output);
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
