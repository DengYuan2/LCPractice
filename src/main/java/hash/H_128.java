package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// 最长连续序列 要求时间复杂度为O(n)
// https://leetcode-cn.com/problems/longest-consecutive-sequence/description/
public class H_128 {
    public static void main(String[] args) {
        H_128 h = new H_128();
        int[] nums = {99,100, 4,102, 200,103, 1,101, 3, 2};
        int[] nums2={1,2,0,1};
        int[] nums3={1,3,5,2,4};
        int[] nums4={0,3,7,2,5,8,4,6,0,1};
        int res1 = h.longestConsecutive1(nums4);
        System.out.println(res1);
        int res2 = h.longestConsecutive2(nums4);
        System.out.println(res2);
    }

    //自己写了半天不对，感觉差半边
    //大神的写法
    public int longestConsecutive1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num:nums) {
            map.put(num,1);
        }
        for (int num:nums ) {
            helper(map,num);
        }
        int max= 0;
        for (int len:map.values() ) {
            max=Math.max(len,max);
        }
        return max;

    }

    //这一步的处理很是巧妙
    public int helper(HashMap<Integer,Integer> map, int num){
        if (!map.containsKey(num)){
            return 0;
        }
        int cnt = map.get(num);
        if (cnt>1){
            return cnt;
        }
        cnt=helper(map,num+1)+1;
        map.put(num,cnt);
        return cnt;
    }

    //官方使用的set，感觉思路更简单
    public int longestConsecutive2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n:nums) {
            set.add(n);
        }
        int curNum=0;
        int curLen=1;
        int max=0;
        for (int num:set) {
            // 简化运算：有更小的数的话，就从最小的数开始计算，因为肯定大于该数的结果啦
            if (!set.contains(num-1)){
                curNum=num;
                while (set.contains(curNum+1)){
                    curNum+=1;
                    curLen+=1;
                }
                max=Math.max(curLen,max);
                curLen=1;
            }

        }
        return max;

    }


}
