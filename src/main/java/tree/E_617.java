package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

// 合并二叉树
// https://leetcode-cn.com/problems/merge-two-binary-trees/description/
public class E_617 {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 2, 5};
        int[] arr2 = {2, 1, 3, 655, 4, 655, 7}; //null节点的值用655表示
        General g = new General();
        TreeNode tree1 = g.buildTree(arr1);
        TreeNode tree2 = g.buildTree(arr2);
        System.out.println("第一棵树为：");
        g.preList(tree1);
        System.out.println("第二棵树为：");
        g.preList(tree2);

        System.out.println("合并两棵树咯");
        E_617 e = new E_617();
//        System.out.println("递归方法；");
//        TreeNode trees1 = e.mergeTrees(tree1, tree2);
//        g.preList(trees1);
        //不能和递归方法一起测试，因为第一棵树已经变了
        System.out.println("迭代方法：");
        TreeNode trees2 = e.mergeTrees2(tree1, tree2);
        g.preList(trees2);
    }

    //我没有思路，后来看了官方的解析
    //看着官方递归的思路自己写的，还有官方写的，更简洁
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        //自己写的
//        TreeNode node =null;
//        if (t1!=null&&t2!=null){ //这个if其实没有必要，因为能走到这里肯定是因为这样
//            node = new TreeNode(t1.val + t2.val);
//            node.left = mergeTrees(t1.left, t2.left);
//            node.right = mergeTrees(t1.right, t2.right);
//        }
//        return node;
        //官方的
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    //迭代法，照着敲的
    //暂时还没看懂呢，明天再看吧
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
       if (t1==null){
           return t2;
       }
        Stack<TreeNode[]> stack = new Stack<TreeNode[]>();
        stack.push(new TreeNode[]{t1,t2});
        while (!stack.isEmpty()){
            TreeNode[] t = stack.pop();
            if (t[0]==null||t[1]==null){
                continue;
            }
            t[0].val+=t[1].val;
            if (t[0].left==null){
                t[0].left=t[1].left;
            }else {
                stack.push(new TreeNode[]{t[0].left,t[1].left});
            }
            if (t[0].right==null){
                t[0].right=t[1].right;
            }else {
                stack.push(new TreeNode[]{t[0].right,t[1].right});
            }
        }
        return t1;
    }
}