package tree;

import java.util.HashMap;
import java.util.Map;

// 统计路径和等于一个数的路径数量
// https://leetcode-cn.com/problems/path-sum-iii/description/
public class E_437 {
    public static void main(String[] args) {
        int[] arr = {10, 5, -3, 3, 2, 655, 11, 3, -2, 655, 1};
//        int[] arr = {1,655,2,655,3,655,4,655,5};
        General g = new General();
        TreeNode node = g.buildTree(arr);
        E_437 e = new E_437();
        int res1 = e.pathSum(node, 18);//3
        System.out.println(res1);
        int res2 = e.pathSum2(node, 18);
        System.out.println(res2);
        int res3 = e.pathSum3(node, 18);
        System.out.println(res3);
        int res4 = e.pathSum4(node, 18);
        System.out.println(res4);
    }

    //想法；分解：计算出根节点到下面各个节点的和，满足则+1；剩余递归即可.即递归套递归
    int count = 0;

    public int pathSum(TreeNode root, int sum) {

        if (root == null) {
            return 0; //反正于count无关
        }
        calculate(root, 0, sum);
        if (root.left != null) {
            pathSum(root.left, sum);
        }
        if (root.right != null) {
            pathSum(root.right, sum);
        }
        return count;
    }


    public int calculate(TreeNode root, int cur, int sum) {
        cur = cur + root.val;
        if (cur == sum) {
            count++;
        }
        if (root.left != null) {
            calculate(root.left, cur, sum);
        }
        if (root.right != null) {
            calculate(root.right, cur, sum);
        }
        return count;
    }

    //大神与我的整体思路一致，但在细节层面，有不少区别
    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = pathSumStartWithRoot(root, sum) + pathSum2(root.left, sum) + pathSum2(root.right, sum);
        return count;

    }

    public int pathSumStartWithRoot(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        count += pathSumStartWithRoot(root.left, sum - root.val) + pathSumStartWithRoot(root.right, sum - root.val);
        return count;
    }


    //官方网友的方法；前缀和+递归+回溯
    //详细思路；https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
    //只是拿过来debug一遍，基本看懂了，但写不出来啊
    public int pathSum3(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return recursionPathSum(root, map, sum, 0);
    }

    public int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        if (node == null) {
            return 0;
        }
        currSum += node.val;
        int res = 0;
        res += prefixSumCount.getOrDefault(currSum - target, 0); //java8中新特性，报错但不影响使用 【map中有key则返回get(key),无则返回0】
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        //为回溯清楚障碍：需要看着图debug理解
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }

    //官方网友的方法；
    //详细思路：https://leetcode-cn.com/problems/path-sum-iii/solution/marveljian-dan-de-xue-xi-bi-ji-437-by-marvel_ty/
    //只是拿过来debug一下
    private int sum;

    public int pathSum4(TreeNode root, int sum) {
        this.sum = sum;
        return getSum(root, new int[1000], 0);
    }

    private int getSum(TreeNode root, int[] vals, int layer) {
        if (root == null) {
            return 0;
        }
        vals[layer] = root.val;
        int cur = 0;
        int temp = 0;
        for (int i = layer; i >= 0; i--) {
            temp += vals[i];
            if (temp == this.sum) {
                cur++;
            }
        }
        return cur + getSum(root.left, vals, layer + 1) + getSum(root.right, vals, layer + 1);
    }

}
