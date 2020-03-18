package Level_1;

import java.util.Arrays;
import java.util.Scanner;

public class E_204 {
    //此为经典方法
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            long startTime = System.currentTimeMillis();
            int count = 0;
            int n = scanner.nextInt();
            boolean[] a = new boolean[n];
            Arrays.fill(a, true);
            for (int i = 2; i * i < n; i++)
                if (a[i])
                    for (int j = i * i; j < n; j += i)
                        a[j] = false;
            for (int p = 2; p < n; p++)
                if (a[p]) count++;
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("所用时间为：" + duration + " ms，得到的结果为" + count);
        }
    }

}
