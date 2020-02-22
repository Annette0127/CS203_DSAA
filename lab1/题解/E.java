import java.util.Scanner;

public class E{
    static int t;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long[] eve = {sc.nextLong(), sc.nextLong()};
        long[] neko = {sc.nextLong(), sc.nextLong()};
        neko[0] = neko[0] - eve[0];
        neko[1] = neko[1] - eve[1];
        t = sc.nextInt();
        long[][] moving = new long[2][t];//0是左右x，1是上下y
        sc.nextLine();
        String a = sc.nextLine();
        for (int i = 0; i < t; i++) {
            if (a.charAt(i) == 'U') {
                if (i != 0) {
                    moving[1][i] = moving[1][i - 1] + 1;
                    moving[0][i] = moving[0][i - 1];
                } else moving[1][i]++;
            } else if (a.charAt(i) == 'D') {
                if (i != 0) {
                    moving[1][i] = moving[1][i - 1] - 1;
                    moving[0][i] = moving[0][i - 1];
                } else moving[1][i]--;
            } else if (a.charAt(i) == 'L') {
                if (i != 0) {
                    moving[0][i] = moving[0][i - 1] - 1;
                    moving[1][i] = moving[1][i - 1];
                } else moving[0][i]--;
            } else if (a.charAt(i) == 'R') {
                if (i != 0) {
                    moving[0][i] = moving[0][i - 1] + 1;
                    moving[1][i] = moving[1][i - 1];
                } else moving[0][i]++;
            }
        }
        long left = 0;
        long right = (long) Math.pow(10, 14);
        long mid;
        if (nekoPosition(right, neko[0], neko[1], moving) > right) System.out.println(-1);

        else {
            for (; left < right; ) {
                mid = left + ((right - left) >> 1);
                if (nekoPosition(mid, neko[0], neko[1], moving) > mid) left = mid;
                else right = mid;
                if ((nekoPosition(left, neko[0], neko[1], moving) > mid) && (nekoPosition(right, neko[0], neko[1], moving) <= right) && right-left == 1)
                    break;
            }
            System.out.println(right);
        }

    }

    public static long nekoPosition(long time, long startX, long startY, long[][] moving) {
        startX = startX + (time / t) * (moving[0][moving[0].length - 1]);
        startY = startY + (time / t) * (moving[1][moving[1].length - 1]);
        if (time % t != 0) {
            startX += moving[0][(int) (time % t - 1)];
            startY += moving[1][(int) (time % t - 1)];
        }
        return Math.abs(startX) + Math.abs(startY);
    }
}