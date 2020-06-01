import java.io.*;
import java.util.*;

public class B {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int cases = in.nextInt();
        int N, M, K;
        for (int i = 0; i < cases; i++) {
            N = in.nextInt();
            M = in.nextInt();
            K = in.nextInt();

            int[][] matrix = new int[K + 2][K + 2];
            int[][] monster = new int[K + 2][3];
            for (int j = 1; j <= K; j++) {
                monster[j][0] = in.nextInt();
                monster[j][1] = in.nextInt();
                monster[j][2] = in.nextInt();


                if (monster[j][2] >= monster[j][0] || monster[j][2] >= (M - monster[j][1])) {
                    matrix[j][K + 1] = 1;
                    matrix[K + 1][j] = 1;
                }
                if (monster[j][2] >= monster[j][1] || monster[j][2] >= (N - monster[j][0])) {
                    matrix[j][0] = 1;
                    matrix[0][j] = 1;
                }
            }
            long sum;
            for (int j = 1; j <= K; j++) {
                for (int k = 1; k <= K; k++) {
                    sum = (long) (monster[j][0] - monster[k][0]) * (monster[j][0] - monster[k][0])
                            + (long) (monster[j][1] - monster[k][1]) * (monster[j][1] - monster[k][1]);
                    if (sum <= (long)(monster[j][2] + monster[k][2])*(monster[j][2] + monster[k][2])) {
                        matrix[j][k] = 1;
                        matrix[k][j] = 1;
                    }
                }
            }
            for (int j = 0; j < K + 2; j++) {
                matrix[j][j] = 0;
            }

            int[] queue = new int[1000000];
            int start = 0, end = 1;
            queue[start] = 0;
            boolean t = false;
            while (start != end) {
                for (int j = 0; j < K + 2; j++) {
                    if (matrix[queue[start]][j] != 0) {
                        if (j == K + 1) {
                            t = true;
                            break;
                        }
                        queue[end] = j;
                        end++;
                        matrix[queue[start]][j] = 0;
                        matrix[j][queue[start]] = 0;
                        if (j == K + 1) t = true;
                    }
                }
                if (t) break;
                start++;
            }
            if (t) out.println("No");
            else out.println("Yes");
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
