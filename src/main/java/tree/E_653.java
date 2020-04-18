package tree;

import java.util.ArrayList;

// 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
// https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/description/
public class E_653 {
    public static void main(String[] args) {
        E_653 e = new E_653();
        int[] arr = {5, 3, 6, 2, 4, 655, 7};
        int[] arr2 = {2, 1, 3};
        General g = new General();
        TreeNode node = g.buildTree(arr2);
        boolean res1 = e.findTarget(node, 4);
        System.out.println(res1);

    }

    // 我的想法：从根节点开始，找它的另一个节点；找不到的话，再从左节点开始找，左节点页找不到，就从其右节点开始找
    // 额，实在没有把我的想法实现，于是看了大神的思路：中序遍历得到数组，然后用双指针查找
    // 下面被注释掉的部分是我写的，太垃圾了，看看大神的写法
    ArrayList<Integer> list = new ArrayList<Integer>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        inOrder(root);
        //大神的写法
        int i = 0;
        int j=list.size()-1;
        while (i<j){
            int sum=list.get(i)+list.get(j);
            if (sum==k){
                return true;
            }
            if (sum<k){
                i++;
            }else {
                j--;
            }
        }
        //我的写法，没有用到从小到大排序这个特征，且没有那种往后走和往前走的思路。
//        if (list.size() < 2) {
//            return false;
//        }
//        for (int i = 0; i < list.size()-1; i++) {
//            for (int j = i+1; j < list.size(); j++) {
//                if (list.get(i)+list.get(j)==k){
//                    return true;
//                }
//            }
//        }

        return false;
    }

    public void inOrder(TreeNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            inOrder(root.right);
        }
    }

}