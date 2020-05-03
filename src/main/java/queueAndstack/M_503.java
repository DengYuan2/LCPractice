package queueAndstack;

import java.util.Arrays;
import java.util.Stack;

// 下一个更大元素
// https://leetcode-cn.com/problems/next-greater-element-ii/description/
public class M_503 {
    public static void main(String[] args) {
        // 1 2 6 4 3 5  4 1
        M_503 m = new M_503();
        int[] a = {1, 2, 3, 2, 1};
        int[] b = {5, 4, 3, 2, 1};
        int[] c = {1, 1, 1, 1, 1};
        int[] res1 = m.nextGreaterElements(a);
        System.out.println(Arrays.toString(res1));

    }

    // 我想到了类似于M_739的思路，但还是做不出来，看了大神的
    // 循环数组：最多看成两个一样的数组嘛！
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < 2 * len; i++) {
            int value = nums[i % len];
            while (!stack.isEmpty() && value > nums[stack.peek()]) {
                res[stack.pop()]=value;
            }
            if (i<len){
                stack.push(i);
            }
        }

        return res;
    }
}
