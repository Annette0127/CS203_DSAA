import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    long weight;
    int start;
    int end;
    int lastpoint;
    int LastEdgeIsi;
    long lastweight;

    public Edge(long w, int s, int e) {
        weight = w;
        start = s;
        end = e;
        lastpoint = -1;
        lastweight = -1;
        LastEdgeIsi = 0;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.weight) return 1;
        else if (this.weight < o.weight) return -1;
        else return 0;
    }
}

public class G {
    public static Edge append(Edge e1, Edge e2) {
        Edge e = new Edge(e1.weight + e2.weight, e1.start, e2.end);
        e.lastpoint = e2.start;
        e.lastweight = e2.weight;
        return e;
    }

    public static Edge turn(Edge e1, Edge e2) {
        return new Edge(e1.weight - e1.lastweight + e2.weight, e1.start, e2.end);
    }

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int campus = in.nextInt();
            int roads = in.nextInt();
            int queries = in.nextInt();
            int s, e;
            long w;
            ArrayList<Edge> ar[] = new ArrayList[campus + 1];//从1开始
            for (int j = 1; j < ar.length; j++) {
                ar[j] = new ArrayList<>();
            }

            for (int j = 1; j <= roads; j++) {
                s = in.nextInt();
                e = in.nextInt();
                w = in.nextLong();
                Edge edge = new Edge(w, s, e);
                ar[s].add(edge);
            }

//            for (int l = 1; l < ar.length; l++) {
//                for (int j = 0; j < ar[l].size(); j++) {
//                    System.out.print(((Edge)ar[l].get(j)).weight+" ");
//                }
//                System.out.println();
//            }

            for (int j = 1; j < ar.length; j++) {
                Collections.sort(ar[j]);
            }

            //
//            for (int l = 1; l < ar.length; l++) {
//                for (int j = 0; j < ar[l].size(); j++) {
//                    System.out.print(((Edge)ar[l].get(j)).weight+" ");
//                }
//                System.out.println();
//            }
            //
            int maxq = -1;
            int query[] = new int[queries];//0开始
            for (int j = 0; j < queries; j++) {
                query[j] = in.nextInt();
                maxq = maxq > query[j] ? maxq : query[j];
            }
            PriorityQueue<Edge> heap = new PriorityQueue();
            long[] result = new long[maxq];//从0开始
//            int[] count = new int[campus + 1];

            for (int l = 1; l < ar.length; l++) {
                for (int j = 0; j < ar[l].size(); j++) {
                    heap.add(ar[l].get(j));
                }
            }
            //
//            for (int p=1;p<=heapEnd;p++) {
//                if (heap[p] != null) System.out.print(heap[p].weight + " ");
//            }
//            System.out.println();
            //
            Edge edge;
            Edge nedge1, nedge2;
            for (int j = 0; j < result.length; j++) {
                edge = heap.poll();
//                System.out.println(edge.start+" "+edge.lastpoint+" "+edge.end+" "+edge.weight);
                //
//                for (int p = 1; p <= heapEnd; p++) {
//                    if (heap[p] != null) System.out.print(heap[p].weight + " ");
//                }
//                System.out.println();
                //
//                System.out.println(j);
                result[j] = edge.weight;
                if (ar[edge.end].size() > 0) {
                    nedge1 = append(edge, ar[edge.end].get(0));
                    heap.add(nedge1);
                }
                if (edge.lastpoint > 0) {
                    if (edge.LastEdgeIsi + 1 < ar[edge.lastpoint].size()) {
                        nedge2 = turn(edge, ar[edge.lastpoint].get(edge.LastEdgeIsi + 1));
                        heap.add(nedge2);
                        nedge2.lastpoint=edge.lastpoint;
                        nedge2.lastweight = ar[edge.lastpoint].get(edge.LastEdgeIsi + 1).weight;
                        nedge2.LastEdgeIsi = edge.LastEdgeIsi+1;
                    }
                }
            }
            for (int j = 0; j < queries; j++) {
                out.println(result[query[j] - 1]);
            }
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