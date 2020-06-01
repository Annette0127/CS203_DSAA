import java.io.*;
import java.util.ArrayList;
import java.util.Stack;


public class C {
    static int count;

    public static void dfs(int u, int[] vis, ArrayList[] graph, int[] sequence) {
        vis[u] = 1;
        for (int i = 0; i < graph[u].size(); i++) {
            if (vis[(int) graph[u].get(i)] < 1) {
                dfs((int) graph[u].get(i), vis, graph, sequence);
            }
        }
        vis[u] = 2;
        sequence[count] = u;
        count++;
    }


    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int node = sc.nextInt();
        int edge = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer>[] graph = new ArrayList[node + 1];
        ArrayList<Integer>[] reGraph = new ArrayList[node + 1];
        int u, v;
        for (int i = 1; i <= node; i++) {
            graph[i] = new ArrayList<>();
            reGraph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= edge; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            graph[u].add(v);
            reGraph[v].add(u);
        }

        int[] sequence = new int[node + 1];
        int[] visit = new int[node + 1];//1 正在访问
        count = 0;
        dfs(1, visit, graph, sequence);

        if (count < node) {
            System.out.println("ovarB");
            return;
        }

//        for (int i = 0; i < sequence.length; i++) {
//            System.out.print(sequence[i] + " ");
//        }
//        System.out.println();

        count = 0;
        for (int i = 0; i < visit.length; i++) {
            visit[i] = 0;
        }
        dfs(1, visit, reGraph, sequence);

//        for (int i = 0; i < sequence.length; i++) {
//            System.out.print(sequence[i] + " ");
//        }

        if (count < node) {
            out.println("ovarB");
        } else {
            out.println("Bravo");
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