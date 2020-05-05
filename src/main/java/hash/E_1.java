package hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// 两数之和
// http://xn--https-lm1h7im17b350b//leetcode-cn.com/problems/two-sum/description/
public class E_1 {
    public static void main(String[] args) {
        E_1 e = new E_1();
        int[] nums={2, 7, 11, 15};
        int[] res1 = e.twoSum(nums, 18);
        System.out.println(Arrays.toString(res1));
        int[] res2 = e.twoSum3(nums, 18);
        System.out.println(Arrays.toString(res2));

    }

    // 我的第一想法：不同map，就一个个查数组
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target){
                    res[0]=i;
                    res[1]=j;
                }
            }
        }
        return res;
    }

    //大神的，使用hashMap，注意；让key=nums[i],value=i
    public int[] twoSum2(int[] nums,int target){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }else {
                map.put(nums[i],i);
            }
        }

        return null;
    }

    //根据大神思路，双指针法
    public int[] twoSum3(int[] nums,int target){
        for (int i = 0; i < nums.length; i++) {
            for (int left = i+1,right=nums.length-1; left<=right; left++,right--) {
                if (nums[left]==target-nums[i]){
                    return new int[]{i,left};
                }else  if(nums[right]==target-nums[i]){
                    return new int[]{i,right};
                }
            }
        }

        return null;
    }




}
