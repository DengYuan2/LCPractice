package tree;

import java.util.Stack;

// 一棵所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值
// https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/description/
public class E_530 {
    public static void main(String[] args) {
        E_530 e = new E_530();
        int[] arr={1,655,3,2};
        General g = new General();
        TreeNode node = g.buildTree(arr);
        int res1 = e.getMinimumDifference1(node);
        System.out.println(res1);
        int res2 = e.getMinimumDifference2(node);
        System.out.println(res2);

    }

    //我的思路是，必定是从小到大排序后相邻两节点之间差的绝对值
    //中序遍历的迭代法
    public int getMinimumDifference1(TreeNode root) {
        //小于两个节点就肯定没有解，就返回-1吧
        if (root==null || (root.left==null&&root.right==null)){
            return -1;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        TreeNode node = root;
        int dif=Integer.MAX_VALUE;

        while (node!=null||!stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node=node.left;
            }
            node= stack.pop();
            if (pre!=null){
                dif=Math.min(node.val-pre.val,dif);
            }
            pre=node;
            node=node.right;
        }
        return dif;
    }

    //大神的思路跟我一致，不过他是用的递归哦,就是中序遍历嘛
    int minDif= Integer.MAX_VALUE;
    TreeNode pre = null;
    public int getMinimumDifference2(TreeNode root) {
        minHelper(root);
        return minDif;
    }

    public void minHelper(TreeNode root){
        if (root==null){
            return;
        }
        minHelper(root.left);
        if (pre!=null){
            minDif=Math.min(minDif,root.val-pre.val);
        }
        pre=root;
        minHelper(root.right);
    }
}
