package tree;

import java.util.Stack;

// 把二叉搜索树转换成累加树
// https://leetcode-cn.com/problems/convert-bst-to-greater-tree/description/
public class E_538 {
    public static void main(String[] args) {
        E_538 e = new E_538();
        General g = new General();
        int[] arr = {5, 2, 13,655,655,7,14};
        TreeNode node = g.buildTree(arr);
//        TreeNode res1 = e.convertBST(node);
//        System.out.println(res1);
        TreeNode res2 = e.convertBST2(node);
        System.out.println(res2);
    }

    // 我的想法；先找最大的。 把中序遍历反过来搞，变成右-左-中先搞右边的，则只要一个个加上它的前一个即可
    // 不过该思路一直没有实现，最后看了大神的。思路一致，但真的是用到了我一直没想到的那一个点
    // 也是官方的一种方法
    int sum = 0; //!!!借助它来改变节点的值啊
    public TreeNode convertBST(TreeNode root) {
        inOrder(root);
        return root;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
            inOrder(root.right);
        root.val+=sum;
        sum=root.val;
            inOrder(root.left);
    }

    int temp = 0;
    //官方方法二：用栈和迭代进行中序遍历
    public TreeNode convertBST2(TreeNode root){
        if (root==null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node!=null){
            while (node!=null){
                stack.push(node);
                node=node.right;
            }
            TreeNode pop = stack.pop();
            pop.val+=temp;
            temp=pop.val;
            node=pop.left;
        }
        return root;
    }

}
