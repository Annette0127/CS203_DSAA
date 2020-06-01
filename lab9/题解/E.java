//import java.io.*;
//import java.util.*;
//
//
//class Edge implements Comparable<Edge> {
//    long weight;
//    int start;
//    int previous;
//    int end;
//
//    public Edge(int s, int p, int e, long w) {
//        start = s;
//        previous = p;
//        end = e;
//        weight = w;
//    }
//
//    @Override
//    public int compareTo(Edge o) {
//        if (this.weight > o.weight) return 1;
//        else if (this.weight == o.weight) return 0;
//        else return -1;
//    }
//}
//
//public class E {
//    public static void dij(int node, int n, int k, int count, ArrayList[] relation, long[][] d, ArrayList[] ports) {
//        boolean visit[] = new boolean[n];
//        PriorityQueue<Edge> heap = new PriorityQueue<>();
//        Edge e;
//        heap.add(new Edge(node, -1, node, d[count][node]));
//
//        while (!heap.isEmpty()) {
//            e = heap.poll();
//            if (visit[e.end]) continue;
//            visit[e.end] = true;
//            Edge eN;
//            for (int i = 0; i < relation[e.end].size(); i++) {
//                if (d[count][((Edge) relation[e.end].get(i)).end] > d[count][e.end] + ((Edge) relation[e.end].get(i)).weight) {
//                    d[count][((Edge) relation[e.end].get(i)).end] = d[count][e.end] + ((Edge) relation[e.end].get(i)).weight;
////                    System.out.println("+++"+d[1][2]);
//                    eN = new Edge(e.start, e.end, ((Edge) relation[e.end].get(i)).end, d[count][e.end] + ((Edge) relation[e.end].get(i)).weight);
//                    heap.add(eN);
//                }
//            }
//            if (count >= k) continue;
//            for (int i = 0; i < ports[e.end].size(); i++) {
////                System.out.println(count+"***"+d[1][2]);
//                if (d[count + 1][(int) ports[e.end].get(i)] > d[count][e.end]) {
//                    d[count + 1][(int) ports[e.end].get(i)] = d[count][e.end];
////                    System.out.println(count+"***"+d[1][2]);
//                    dij((int) ports[e.end].get(i), n, k, count + 1, relation, d, ports);
//                }
//            }
//        }
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        Reader sc = new Reader();
//        PrintWriter out = new PrintWriter(System.out);
//
//        int point = sc.nextInt();
//        int edge = sc.nextInt();
//        int portal = sc.nextInt();
//        int k = sc.nextInt();
//        ArrayList<Edge>[] relation = new ArrayList[point];
//        ArrayList<Integer>[] ports = new ArrayList[point];
//        long d[][] = new long[k + 1][point];//记录路径长度
//
//
//        for (int i = 0; i < point; i++) {
//            relation[i] = new ArrayList<>();
//            ports[i] = new ArrayList<>();
//        }
//
//        int start, end;
//        int a, b;
//        long w;
//        for (int i = 0; i < edge; i++) {
//            a = sc.nextInt() - 1;
//            b = sc.nextInt() - 1;
//            w = sc.nextLong();
//            relation[a].add(new Edge(a, -1, b, w));
//        }
//        for (int i = 0; i < portal; i++) {
//            a = sc.nextInt() - 1;
//            b = sc.nextInt() - 1;
//            ports[a].add(b);
//        }
//        start = sc.nextInt() - 1;
//        end = sc.nextInt() - 1;
//        for (int i = 0; i < point; i++) {
//            for (int j = 0; j <= k; j++) {
//                d[j][i] = Long.MAX_VALUE;
//            }
//        }
//
//        d[0][start] = 0;
//        dij(start, point, k, 0, relation, d, ports);
//
//        long min = d[0][end];
//        for (int i = 1; i <= k; i++) {
//            min = d[i][end] < min ? d[i][end] : min;
//        }
//
////        for (int i = 0; i <= k; i++) {
////            System.out.print(i + ": ");
////            for (int j = 0; j < point; j++) {
////                System.out.print(d[i][j] + " ");
////            }
////            System.out.println();
////        }
//
//        out.println(min);
//
//        out.close();
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