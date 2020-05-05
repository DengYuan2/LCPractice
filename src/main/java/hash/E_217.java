package hash;

import java.util.HashSet;

// 存在重复元素
// https://leetcode-cn.com/problems/contains-duplicate/description/
public class E_217 {
    public static void main(String[] args) {
        E_217 e = new E_217();
        int[] nums = {1, 1, 3, 3, 4, 3, 2, 4, 2};
        boolean res1 = e.containsDuplicate(nums);
        System.out.println(res1);
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    //同样是用set，大神的写法
    public boolean containsDuplicate2(int[] nums){
        HashSet<Integer> set = new HashSet<Integer>();
        for (int n:nums) {
            set.add(n);
        }
        return set.size()<nums.length;
    }
}
