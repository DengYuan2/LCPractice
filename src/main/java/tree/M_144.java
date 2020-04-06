package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 二叉树的前序遍历（要求非递归）
// https://leetcode-cn.com/problems/binary-tree-preorder-traversal/description/
public class M_144 {
    public static void main(String[] args) {
        M_144 m = new M_144();
        General g = new General();
        int[] arr={1,655,2,3};
        TreeNode node = g.buildTree(arr);
        List<Integer> res1 = m.preorderTraversal1(node);
        System.out.println(res1);
        List<Integer> res2 = m.preorderTraversal2(node);
        System.out.println(res2);

    }


    //我的写法，和官方的迭代法一致
    public List<Integer> preorderTraversal1(TreeNode root) {

        List<Integer> list = new LinkedList<Integer>(); //必须要放在上面，因为如果根节点为空，应该返回的是[],而非null
        if (root==null){
            return list; //不能返回null啊，理由见上行注释
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        TreeNode cur=null;
        while (!stack.isEmpty()){
            cur=stack.pop();
            list.add(cur.val);
            if (cur.right!=null){
                stack.add(cur.right);
            }
            if (cur.left!=null){
                stack.add(cur.left);
            }

        }
        return list;
    }

    //大神的写法：将空间点也放入栈中了
    public List<Integer> preorderTraversal2(TreeNode root){
        LinkedList<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            if (pop==null){
                continue;
            }
            list.add(pop.val);
            stack.add(pop.right);
            stack.add(pop.left);
        }
        return list;
    }

    //官方的莫里斯遍历,虽然很好，但没有理解其思想，因为要看莫里斯的论文才能理解吧
    //反正不理解，只是记住也没用，就不写了
    //有空可以去了解一下，毕竟是通用的方法啊
//    public List<Integer> preorderTraversal3(TreeNode root){
//
//    }
}
