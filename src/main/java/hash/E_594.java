package hash;

import java.util.*;

// 最长和谐子序列
// 注意，要求最大值和最小值之差必须为1，不是<=1
// https://leetcode-cn.com/problems/longest-harmonious-subsequence/description/
public class E_594 {
    public static void main(String[] args) {
        E_594 e = new E_594();
        int[] nums1 = {1, 3, 2, 2, 5, 2, 3, 7};
        int[] nums2 = {1,1,1,1};
        int[] nums3={1,3};
        int res1 = e.findLHS(nums3);
        System.out.println(res1);
        int res2 = e.findLHS2(nums3);
        System.out.println(res2);
    }

    //我的想法，用map,以 数值=该值的出现次数为键值对放入map中，则只要遍历map，每一对：查看 get(该数)+get(该数-1) 或 get(该数)+get(该数+1)，得到该值的和谐序列个数；再对每一对都如此比较
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        int max = 0;
        while (iterator.hasNext()) {
            int num = iterator.next();
            int now = map.get(num);
            int aft = map.get(num+1)==null?0:map.get(num+1);
            int bef = map.get(num-1)==null?0:map.get(num-1);
            if (aft!=0 ||bef!=0){
                max = Math.max(max, now+bef);
                max=Math.max(max,now+aft);
            }
        }
        return max;
    }

    //大神的写法：写法简单。且只查看map(该值)+map(该值+1)
    //扫描了一次数组和一次map
    public int findLHS2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        //这一步就比我的简洁了
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        int max=0;

        for (Integer num:map.keySet()) {
            //这里面，很巧妙，只比较num+1哦
            if (map.containsKey(num+1)){
                max=Math.max(max,map.get(num)+map.get(num+1));
            }
        }
        return max;


    }

    //官方第三种解法，一共只用扫描一遍（只扫描了一次数组）
    //但查询次数多了一倍
    public int findLHS3(int[] nums){
        HashMap<Integer, Integer> map = new HashMap<>();
        int max=0;
        for (int num:nums) {
            map.put(num,map.getOrDefault(num,0)+1);
            if (map.containsKey(num+1)){
                max=Math.max(max,map.get(num)+map.get(num+1));
            }
            if (map.containsKey(num-1)){
                max=Math.max(max,map.get(num)+map.get(num-1));
            }
        }
        return max;

    }
    //额，感觉我的写法像这两个的混合体，归根结底是因为思路不清晰啊
}
