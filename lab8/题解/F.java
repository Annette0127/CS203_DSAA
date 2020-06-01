import java.io.*;
import java.util.*;

public class F {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int block = in.nextInt();
            int[][] relation = new int[block][block];
            int[][] blocks = new int[block][5];
            int[] max = new int[block];
            for (int j = 0; j < block; j++) {
                blocks[j][0] = in.nextInt();//a
                blocks[j][1] = in.nextInt();//b
                blocks[j][2] = in.nextInt();//c
                max[j] = blocks[j][2];
            }

            ArrayList<Integer>[] ar = new ArrayList[block];
            ArrayList<Integer>[] arback = new ArrayList[block];
            int[][] degree = new int[block][2];//0 in, 1 out;
            for (int j = 0; j < arback.length; j++) {
                ar[j] = new ArrayList<>();
                arback[j] = new ArrayList<>();
            }

            for (int j = 0; j < block; j++) {
                for (int k = j; k < block; k++) {
                    if ((blocks[j][0] > blocks[k][0] && blocks[j][1] > blocks[k][1])
                            || (blocks[j][1] > blocks[k][0] && blocks[j][0] > blocks[k][1])) {//j在k下面
                        ar[j].add(k);
                        arback[k].add(j);
                        degree[k][1]++;
                        degree[j][0]++;
                    } else if ((blocks[j][0] < blocks[k][0] && blocks[j][1] < blocks[k][1])
                            || (blocks[j][1] < blocks[k][0] && blocks[j][0] < blocks[k][1])) {//k在j下面
                        ar[k].add(j);
                        arback[j].add(k);
                        degree[j][1]++;
                        degree[k][0]++;
                    }
                }
            }
//
//            for (int l = 0; l < arback.length; l++) {
//                for (int j = 0; j < arback[l].size(); j++) {
//                    System.out.print(l+":"+arback[l].get(j) + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//
//            for (int l = 0; l < ar.length; l++) {
//                for (int j = 0; j < ar[l].size(); j++) {
//                    System.out.print(l+":"+ar[l].get(j) + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            //
            boolean[] visit = new boolean[block];

            int[] queue = new int[block];
            int start = 0, end = 0;//队列头有东西，队尾为空

            for (int j = 0; j < ar.length; j++) {
                if (ar[j].size() == 0) {
                    queue[end] = j;
                    end++;
                    visit[j] = true;
                }
            }//初始化队列


            int tmp, sum;
            while (start != end) {

//                System.out.print("queue:");
//                for (int k = start; k < end; k++) {
//                    System.out.print(queue[k]+" ");
//                }
//                System.out.println();

                tmp = queue[start];
                start++;
                for (int j = 0; j < arback[tmp].size(); j++) {
                    degree[arback[tmp].get(j)][0]--;
                    sum = max[tmp] + blocks[arback[tmp].get(j)][2];
                    if (sum > max[arback[tmp].get(j)]) max[arback[tmp].get(j)] = sum;

//                    for (int k = 0; k < max.length; k++) {
//                        System.out.print(max[k] + " ");
//                    }
//                    System.out.println("----------");

                    if (degree[arback[tmp].get(j)][0] == 0 && !visit[arback[tmp].get(j)]) {
                        queue[end] = arback[tmp].get(j);
                        visit[arback[tmp].get(j)] = true;
                        end++;
                    }
                }
            }
            int maxre = -1;

            for (int j = 0; j < max.length; j++) {
                maxre = maxre > max[j] ? maxre : max[j];
            }

            out.println(maxre);
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
