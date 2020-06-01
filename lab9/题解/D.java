import java.io.*;
import java.util.*;


class Edge implements Comparable<Edge> {
    long weight;
    Node start;
    Node end;

    public Edge(Node s, Node e, long w) {
        start = s;
        end = e;
        weight = w;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.weight) return -1;
        else if (this.weight == o.weight) return 0;
        else return 1;
    }
}

class Node {
    long cof;
    int row;
    int col;
    boolean visit;

    public Node(int r, int c, long coef) {
        row = r;
        col = c;
        cof = coef;
        visit = false;
    }
}

public class D {
    public static void add(PriorityQueue<Edge> heap, Node point, Node[][] squar) {
        point.visit = true;
        if (point.row == 0 && point.row == squar.length - 1) {
        } else if (point.row == squar.length - 1) {
            if (!squar[point.row - 1][point.col].visit) {
                heap.add(new Edge(point, squar[point.row - 1][point.col], squar[point.row - 1][point.col].cof * point.cof));
            }
        } else if (point.row == 0) {
            if (!squar[point.row + 1][point.col].visit) {
                heap.add(new Edge(point, squar[point.row + 1][point.col], squar[point.row + 1][point.col].cof * point.cof));
            }
        } else {
            if (!squar[point.row - 1][point.col].visit) {
                heap.add(new Edge(point, squar[point.row - 1][point.col], squar[point.row - 1][point.col].cof * point.cof));
            }
            if (!squar[point.row + 1][point.col].visit) {
                heap.add(new Edge(point, squar[point.row + 1][point.col], squar[point.row + 1][point.col].cof * point.cof));
            }
        }

        if (point.col == squar[0].length - 1 && point.col == 0) {
        } else if (point.col == squar[0].length - 1) {
            if (!squar[point.row][point.col - 1].visit) {
                heap.add(new Edge(point, squar[point.row][point.col - 1], squar[point.row][point.col - 1].cof * point.cof));
            }
        } else if (point.col == 0) {
            if (!squar[point.row][point.col + 1].visit) {
                heap.add(new Edge(point, squar[point.row][point.col + 1], squar[point.row][point.col + 1].cof * point.cof));
            }
        } else {
            if (!squar[point.row][point.col + 1].visit) {
                heap.add(new Edge(point, squar[point.row][point.col + 1], squar[point.row][point.col + 1].cof * point.cof));
            }
            if (!squar[point.row][point.col - 1].visit) {
                heap.add(new Edge(point, squar[point.row][point.col - 1], squar[point.row][point.col - 1].cof * point.cof));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int m = sc.nextInt();
        int n = sc.nextInt();
        Node[][] squar = new Node[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                squar[i][j] = new Node(i, j, sc.nextInt());
            }
        }

        long maxEdge = 0;
        Node maxPoint1 = null, maxPoint2 = null;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (squar[i][j].cof * squar[i][j + 1].cof > maxEdge) {
                    maxEdge = squar[i][j].cof * squar[i][j + 1].cof;
                    maxPoint1 = squar[i][j];
                    maxPoint2 = squar[i][j + 1];
                }
            }
        }
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (squar[i][j].cof * squar[i + 1][j].cof > maxEdge) {
                    maxEdge = squar[i][j].cof * squar[i + 1][j].cof;
                    maxPoint1 = squar[i][j];
                    maxPoint2 = squar[i + 1][j];
                }
            }
        }

        PriorityQueue<Edge> heap = new PriorityQueue();
        heap.add(new Edge(maxPoint1, maxPoint2, maxEdge));
//        System.out.println(maxPoint1.cof + " --- " + maxPoint2.cof + "   " + maxEdge);
        maxPoint1.visit = true;
        maxPoint2.visit = true;
        add(heap, maxPoint1, squar);
        add(heap, maxPoint2, squar);
        long count = maxEdge;
        Edge e;
        while (!heap.isEmpty()) {
            e = heap.poll();
            if(!e.end.visit){
//                System.out.println(e.start.cof + " --- " + e.end.cof + "   " + e.weight);
                count += e.weight;
                e.end.visit = true;
                add(heap, e.end, squar);
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