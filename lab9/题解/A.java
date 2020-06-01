import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    long weight;
    int start;
    int previous;
    int end;

    public Edge(int s, int p, int e, long w) {
        start = s;
        previous = p;
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

public class A {
    static long MAX_INT = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter out = new PrintWriter(System.out);


        int point = sc.nextInt();
        int edge = sc.nextInt();
        long d[] = new long[point];//记录路径长度
        for (int i = 0; i < point; i++) {
            d[i] = MAX_INT;
        }
        long path[][] = new long[point][point];
        int start, end;
        boolean[] visit = new boolean[point];
        PriorityQueue<Edge> heap = new PriorityQueue();
        int a, b;
        long w;
        for (int i = 0; i < edge; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            w = sc.nextLong();
            if (path[a - 1][b - 1] == 0 || path[a - 1][b - 1] > w) {
                path[a - 1][b - 1] = w;
                path[b - 1][a - 1] = w;
            }

        }
        start = sc.nextInt() - 1;
        end = sc.nextInt() - 1;
        visit[start] = true;
        d[start] = 0;

        for (int i = 0; i < path.length; i++) {
            Edge e = new Edge(start, -1, start, 0);
            heap.add(e);
//            if(path[0][i]!=0){
//                Edge e = new Edge(start, -1, i, path[0][i]);
//                heap.add(e);}

        }
        Edge e;

        while (!heap.isEmpty()) {
            e = heap.poll();
            visit[e.end] = true;
            for (int i = 0; i < point; i++) {
                if (path[e.end][i] > 0) {
                    if (d[i] > d[e.end] + path[e.end][i]) {
                        d[i] = d[e.end] + path[e.end][i];
                        Edge eN = new Edge(e.start, e.end, i, d[i]);
                        heap.add(eN);
                    }
                }
            }
        }
//        for (int i = 0; i < d.length; i++) {
//            System.out.println((1 + i) + ":" + d[i] + " ");
//            System.out.println(visit[i]);
//        }
//        System.out.println();

        if (!visit[end]) out.println(-1);
        else out.println(d[end]);


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