import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();
        int m = in.nextInt();
        int[][] q = new int[m][3];
        int count2 = 0;
        for (int i = 0; i < m; i++) {
            q[i][0] = in.nextInt();
            q[i][1] = in.nextInt();
            q[i][2] = in.nextInt();
            if (q[i][2] == 2) count2++;
        }

        int newnode = n + 1;
        ArrayList<Integer>[] list = new ArrayList[n + 1 + count2];
        for (int i = 1; i < n + 1 + count2; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            if (q[i][2] == 1) {
                list[q[i][0]].add(q[i][1]);
            } else {
                list[q[i][0]].add(newnode);
                list[newnode].add(q[i][1]);
                newnode++;
            }
        }

        int[] queue = new int[101 + count2 + n];
        boolean[] visit = new boolean[count2 + n + 1];
        int start = 0, end = 1;
        queue[start] = 1;
        visit[1] = true;
        int[] length = new int[count2 + n + 1];
        boolean t = false;

//        for (int l = 1; l <= n + count2; l++) {
//            for (int i = 0; i < list[l].size(); i++) {
//                out.print(l + ":");
//                out.print(list[l].get(i) + " ");
//            }
//            out.println();
//        }

        while (start != end) {
            while (list[queue[start]].size() != 0) {
                if (queue[start] == n) {
                    t = true;
                    break;
                }
                if (!visit[list[queue[start]].get(0)]) {
                    length[list[queue[start]].get(0)] = length[queue[start]] + 1;
                    queue[end] = list[queue[start]].get(0);
                    visit[list[queue[start]].get(0)] = true;
                    end++;
                }
                list[queue[start]].remove(0);
            }
            if (t) break;
//            out.print("queue:");
//            for (int i = start; i < end; i++) {
//                out.print(queue[i]+" ");
//            }
//            out.println();

            start++;
        }
        if(length[n]==0)out.println(-1);
        else out.println(length[n]);
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
