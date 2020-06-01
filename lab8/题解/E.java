import java.io.*;
import java.util.*;

public class E{

    public static void insert(int[] arr, int size, int element) {
        arr[size] = element;
        int child = size, parent = child / 2;
        while (parent > 0) {
            if (arr[child] > arr[parent]) {
                swap(arr, child, parent);
                child = parent;
                parent /= 2;
            } else break;
        }
    }

    public static int delete(int[] arr, int size) {
        int result = arr[1];
        arr[1] = arr[size];
        size--;
        int i = 1;
        while (i > 0)
            i = changeX(arr, i, size);
        return result;
    }

    public static int changeX(int[] arr, int index, int size) {
        if (index * 2 > size) return -1;
        if (index * 2 == size) {
            if (arr[index] < arr[index * 2]) {
                swap(arr, index, index * 2);
                return index * 2;
            } else return -1;
        }
        if (arr[index] < arr[index * 2] && arr[index * 2] >= arr[index * 2 + 1]) {
            swap(arr, index, index * 2);
            return index * 2;
        }
        if (arr[index] < arr[index * 2 + 1] && arr[index * 2 + 1] > arr[index * 2]) {
            swap(arr, index, index * 2 + 1);
            return index * 2 + 1;
        }
        return -1;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);

        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = in.nextInt();//人
            int m = in.nextInt();//边
            ArrayList<Integer>[] ar = new ArrayList[n + 1];
            int[][] degree = new int[n + 1][2];//0 in, 1 out;
            for (int j = 1; j < ar.length; j++) {
                ar[j] = new ArrayList<>();
            }
            int from, to;

            for (int j = 1; j <= m; j++) {//反向存图
                to = in.nextInt();
                from = in.nextInt();
                ar[from].add(to);
                degree[from][1]++;
                degree[to][0]++;
            }

//            for (int l = 1; l < ar.length; l++) {
//                for (int j = 0; j < ar[l].size(); j++) {
//                    System.out.print(l+":"+ar[l].get(j) + " ");
//                }
//                System.out.println();
//            }

//            for (int j = 1; j <7; j++) {
//                System.out.println(degree[j][0] + "---" + degree[j][1]);
//            }

            int[] heap = new int[2 * n];
            int heapEnd = 0;
            for (int j = 1; j <= n; j++) {
                if (degree[j][0] == 0) {
                    heapEnd++;
                    insert(heap, heapEnd, j);
//                    for (int p = 1; p <= heapEnd; p++) {
//                        if (heap[p] != 0) System.out.print(heap[p] + " ");
//                    }
//                    System.out.println("--------" + heapEnd);
                }
            }
            int tmp;
            int[] result = new int[n];
            int count = 0;
            while (heapEnd > 0) {
                tmp = delete(heap, heapEnd);
                heapEnd--;
//                System.out.println("-------delete--------");
//                for (int p = 1; p <= heapEnd; p++) {
//                    if (heap[p] != 0) System.out.print(heap[p] + " ");
//                }
//                System.out.println("--------" + heapEnd);

                result[count] = tmp;
                count++;

                for (int j = 0; j < ar[tmp].size(); j++) {
                    degree[ar[tmp].get(j)][0]--;
                    if (degree[ar[tmp].get(j)][0] == 0) {
                        heapEnd++;
                        insert(heap, heapEnd, ar[tmp].get(j));


//                        System.out.println("-------add--------");
//                        for (int p = 1; p <= heapEnd; p++) {
//                            if (heap[p] != 0) System.out.print(heap[p] + " ");
//                        }
//                        System.out.println("--------"+heapEnd);

                    }
                }
            }
            for (int j = result.length - 1; j >= 0; j--) {
                out.print(result[j]+" ");
            }
            out.println();

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