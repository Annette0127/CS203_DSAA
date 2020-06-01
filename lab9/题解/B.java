import java.io.*;
import java.util.PriorityQueue;


class Edge implements Comparable<Edge> {
    long weight;
    int start;
    int end;

    public Edge(int s, int e, long w) {
        start = s;
        end = e;
        weight = w;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.weight) return 1;
        else if (this.weight == o.weight) return 0;
        else return -1;
    }
}

public class B {

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int point = sc.nextInt();
        int edge = sc.nextInt();
        PriorityQueue<Edge> heap = new PriorityQueue();
        boolean[] points = new boolean[point];//false：没访问过；true：访问过
        long[][] path = new long[point][point];
        int a, b;
        long w = -1;
        int mina = -1, minb = -1;
        long minW = Long.MAX_VALUE;
        for (int i = 0; i < edge; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            w = sc.nextLong();

            if (path[a - 1][b - 1] == 0 || path[a - 1][b - 1] > w) {
                path[a - 1][b - 1] = w;
                path[b - 1][a - 1] = w;
                if (w < minW) {
                    minW = w;
                    mina = a - 1;
                    minb = b - 1;
                }
            }
        }

        long count = minW;
        points[mina] = true;
        points[minb] = true;
        for (int i = 0; i < point; i++) {
            if (path[mina][i] != 0) {
                heap.add(new Edge(mina, i, path[mina][i]));
            }
            if (path[minb][i] != 0) {
                heap.add(new Edge(minb, i, path[minb][i]));
            }
        }
//        System.out.println(" ++"+mina+" "+minb+" "+minW);
        Edge e;
        while (!heap.isEmpty()) {
            e = heap.poll();
            if (!points[e.end]) {
                points[e.end] = true;
                count += e.weight;
//                System.out.println(" ++"+e.start+" "+e.end+" "+e.weight);
                for (int j = 0; j < point; j++) {
                    if (path[e.end][j] != 0) {
                        heap.add(new Edge(e.end, j, path[e.end][j]));
                    }
                }
            }
        }

        out.println(count);

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