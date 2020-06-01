import java.io.*;
import java.util.*;

class Node {
    long x;
    long y;
    int t;
    long r;
    int group;

    Node(long x, long y, long r, int n) {
        this.x = x;
        this.y = y;
        this.t = n;
        this.r = r;
        group = -1;
    }
}

public class Main {
    static int count;
    static int min;
    static int groupCount;

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

    public static void dfs2(int u, int[] vis, ArrayList[] graph, Node[] nodes) {
        vis[u] = 1;
        if (min == -1 || min > nodes[u].t) min = nodes[u].t;
        for (int i = 0; i < graph[u].size(); i++) {
            if (vis[(int) graph[u].get(i)] < 1) {
                dfs2((int) graph[u].get(i), vis, graph, nodes);
            }
        }
        vis[u] = 2;
        nodes[u].group = groupCount;
    }


    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int node = sc.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[node];
        ArrayList<Integer>[] reGraph = new ArrayList[node];
        Node[] nodes = new Node[node];
        for (int i = 0; i < node; i++) {
            nodes[i] = new Node(sc.nextLong(), sc.nextLong(), sc.nextLong(), sc.nextInt());
        }
        for (int i = 0; i < node; i++) {
            graph[i] = new ArrayList<>();
            reGraph[i] = new ArrayList<>();
        }
        long sum;
        for (int i = 0; i < node; i++) {
            for (int j = i + 1; j < node; j++) {
                sum = (nodes[i].x - nodes[j].x) * (nodes[i].x - nodes[j].x) + (nodes[i].y - nodes[j].y) * (nodes[i].y - nodes[j].y);
//                System.out.println(i+" "+j+":"+sum+"  "+(nodes[i].r)*(nodes[i].r)+"  "+(nodes[j].r)*(nodes[j].r));
                if (sum <= (nodes[i].r) * (nodes[i].r)) {
//                    System.out.println(i + " " + j + ":" + sum + "  " + (nodes[i].r) * (nodes[i].r) + "  " + (nodes[j].r) * (nodes[j].r));

                    graph[i].add(j);
                    reGraph[j].add(i);
                }
                if (sum <= (nodes[j].r) * (nodes[j].r)) {
//                    System.out.println(j + " " + i + ":" + sum + "  " + (nodes[j].r) * (nodes[j].r) + "  " + (nodes[i].r) * (nodes[i].r));

                    graph[j].add(i);
                    reGraph[i].add(j);
                }
            }
        }

//        for (int i = 0; i < graph.length; i++) {
//            for (int j = 0; j < graph[i].size(); j++) {
//                System.out.print(graph[i]);
//            }
//            System.out.println();
//        }


        count = 0;
        int[] sequence1 = new int[node];
        int[] visit = new int[node];

        for (int i = 0; i < visit.length; i++) {
            if (visit[i] == 0) dfs(i, visit, reGraph, sequence1);
        }

//        for (int i = 0; i < sequence1.length; i++) {
//            System.out.print(sequence1[i] + " ");
//        }
//        System.out.println();

        //第二遍
        ArrayList<Integer> minVal = new ArrayList<Integer>();
        for (int i = 0; i < visit.length; i++) {
            visit[i] = 0;
        }
        count--;
        groupCount = -1;
//        System.out.println("++++++++++"+count);
        for (int i = count; i >= 0; i--) {
            min = -1;
            if (visit[sequence1[i]] == 0) {
                groupCount++;
                dfs2(sequence1[i], visit, graph, nodes);
            }
            if (min != -1) minVal.add(min);
        }
//        for (int i = 0; i < minVal.size(); i++) {
//            System.out.println(minVal.get(i)+" ");
//        }

//        for (int i = 0; i < node; i++) {
//            System.out.print(nodes[i].group+" ");
//        }
//        System.out.println();
//        for (int i = 0; i < visit.length; i++) {
//            System.out.print(visit[i]);
//        }

        HashSet<Integer>[] relation = new HashSet[groupCount + 1];
        for (int i = 0; i < relation.length; i++) {
            relation[i] = new HashSet<Integer>();
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                if (nodes[graph[i].get(j)].group != nodes[i].group) {
                    relation[nodes[graph[i].get(j)].group].add(nodes[i].group);
                }
            }
        }
//        for (int i = 0; i < relation.length; i++) {
//            System.out.println(relation[i]);
//        }
        long result = 0;
        for (int i = 0; i < relation.length; i++) {
            if (relation[i].size() == 0) result += minVal.get(i);
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
