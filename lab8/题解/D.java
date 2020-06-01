import java.io.*;
import java.util.*;

public class D {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int cases = in.nextInt();
        int n, m, a, b;
        for (int i = 0; i < cases; i++) {
            n = in.nextInt();
            m = in.nextInt();
            int[][] matrix = new int[n + 1][n + 1];
            for (int j = 0; j < m; j++) {
                a = in.nextInt();
                b = in.nextInt();
                matrix[a][b] = 1;
                matrix[b][a] = 1;
            }

            int kind1 = 0, kind0 = 0;
            int[][] result = new int[n + 1][2];
            int[] queue = new int[n + 5];
            int start = 0, end = 1;
            queue[start] = 1;
            result[1][1] = 0;
            result[1][0] = 0;
            while (start != end) {
                for (int j = 1; j <= n; j++) {
                    if (result[j][0] != 0) continue;
                    if (matrix[queue[start]][j] != 0) {

                        result[j][0] = queue[start];
                        queue[end] = j;
                        end++;
                        matrix[queue[start]][j] = 0;
                        matrix[j][queue[start]] = 0;
                    }
                }
                result[queue[start]][1] = (result[result[queue[start]][0]][1] + 1) % 2;



                if (result[queue[start]][1] == 0) {
                    kind0++;
                } else {
                    kind1++;
                }
                start++;
            }

            if (kind0 > kind1) {
                out.println(kind1);
                for (int j = 1; j <= n; j++) {
                    if (result[j][1] == 1) out.print(j + " ");
                }
                out.println();
            } else {
                out.println(kind0);
                for (int j = 1; j <= n; j++) {
                    if (result[j][1] == 0) out.print(j + " ");
                }
                out.println();
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