package queueAndstack;

import java.util.Arrays;
import java.util.Stack;

// 每日温度
// https://leetcode-cn.com/problems/daily-temperatures/description/
public class M_739 {
    public static void main(String[] args) {
        M_739 m = new M_739();
        int[] temp={73, 74, 75, 71, 69, 72, 76, 73};
        int[] res1 = m.dailyTemperatures1(temp);
        System.out.println(Arrays.toString(res1));
        int[] res2 = m.dailyTemperatures2(temp);
        System.out.println(Arrays.toString(res2));
    }

    //我的写法可以是可以，不过好像很垃圾？
    //而且大神是把它归类于栈和队列中的，我却没用到，额。。。。
    public int[] dailyTemperatures1(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i+1; j < T.length; j++) {
                if (T[j]>T[i]){
                    res[i]=j-i;
                    break;
                }
            }
        }
        return res;
    }

    //大神的写法，妙啊
    //官方的解答过于复杂，还是看大神的吧
    public int[] dailyTemperatures2(int[] T){
        int[] res = new int[T.length];
        Stack<Integer> indexStack = new Stack<Integer>();
        for (int i = 0; i < res.length; i++) {
            while (!indexStack.isEmpty()  && T[i]>T[indexStack.peek()]){
                int index = indexStack.pop();
                res[index]=i-index;
            }
            indexStack.add(i);
        }
        return res;
    }
}
