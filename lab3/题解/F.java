import java.io.*;

public class F {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int cases = in.nextInt();

        long[][] sortIn = new long[cases][2];
        List.Item[] address = new List.Item[cases];
        for (int i = 0; i < cases; i++) {
            sortIn[i][0] = in.nextLong();
            sortIn[i][1] = i;
        }
        sortIn = mergeSort(sortIn, cases);
        List numbers = new List();
        for (int i = 0; i < cases; i++) {
            address[(int) sortIn[i][1]] = numbers.add(sortIn[i][0], sortIn[i][1]);
        }
        List.Item p = numbers.getHead();
        long n;
        for (int i = 0; i < cases - 1; i++) {
            out.print(numbers.minValue(address[i])+" ");
            numbers.delet(address[i]);
        }
        out.println();
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

    public static long[][] mergeSort(long[][] A, int n) {
        if (n != 1) {
            int mid = n / 2;
            long[][] B = new long[mid][2];
            long[][] C = new long[n - mid][2];
            for (int i = 0; i < mid; i++) {
                B[i] = A[i];
            }
            for (int i = 0; i < n - mid; i++) {
                C[i] = A[mid + i];
            }
            mergeSort(B, mid);
            mergeSort(C, n - mid);

            int in = 0;
            int l = 0;
            int r = 0;
            while (l < B.length || r < C.length) {
                if (l < B.length && r < C.length) {
                    if (B[l][0] < C[r][0]) {
                        A[in] = B[l];
                        l++;
                    } else {
                        A[in] = C[r];
                        r++;
                    }
                    in++;
                } else {
                    if (l < B.length) {
                        while (in < A.length && l < B.length) {
                            A[in] = B[l];
                            in++;
                            l++;
                        }
                    } else {
                        while (in < A.length && r < C.length) {
                            A[in] = C[r];
                            in++;
                            r++;
                        }
                    }
                }

            }
        }
        return A;
    }

}

class List {
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

    public List.Item getHead() {
        return head;
    }

    public Item add(long num, long no) {
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
        return item;
    }

    public void delet(Item pointer) {
        if (size == 1) {
            head = null;
            tail = null;
        } else if (pointer == head) {
            pointer.next.before = null;
            head = pointer.next;
        } else if (pointer == tail) {
            pointer.before.next = null;
            tail = pointer.before;
        } else {
            pointer.next.before = pointer.before;
            pointer.before.next = pointer.next;
        }
    }

    public long minValue(Item pointer) {
        if (pointer == head && pointer == tail) {
            return -100;
        } else if (pointer == head) {
            return Math.abs(pointer.number - pointer.next.number);
        } else if (pointer == tail) {
            return Math.abs(pointer.number - pointer.before.number);
        } else {
            long v1 = Math.abs(pointer.number - pointer.next.number);
            long v2 = Math.abs(pointer.number - pointer.before.number);
            return Math.min(v1, v2);
        }
    }
}