package tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 二叉树的后序遍历 (要求非递归)
// https://leetcode-cn.com/problems/binary-tree-postorder-traversal/description/
public class H_145 {
    public static void main(String[] args) {
        H_145 h = new H_145();
        General g = new General();
        int[] arr={1,655,2,3};
        int[] arr2={1,2,3,4,5,6,7};
        TreeNode node = g.buildTree(arr2);
//        List<Integer> res1 = h.postorderTraversal1(node);
//        System.out.println(res1); //因为我的方法改变了树的结构，所以不能和下面的一起测试
        List<Integer> res2 = h.postorderTraversal2(node);
        System.out.println(res2);
        List<Integer> res3 = h.postorderTraversal3(node);
        System.out.println(res3);
    }

    //我的写法：虽然通过了，但是改变了树的结构
    public List<Integer> postorderTraversal1(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root==null){
            return list;
        }
        /**
         *           1
         *     2           3
         *  4     5     6     7      //    4 5 2 6 7 3 1
         *
         */
        //当它的左右节点都为空，或左右节点都输出过了[因为在栈中，所以左右节点肯定比它先出，但如何判断它的左右节点都输出过了呢，那就在将左右节点压入栈的同时将其左右节点置为null]，才输出它
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode peek = stack.peek();
            if (peek.right==null&&peek.left==null){
                list.add(stack.pop().val);
                continue;
            }
            if (peek.right!=null){
                stack.push(peek.right);
                peek.right=null;
            }
            if (peek.left!=null){
                stack.push(peek.left);
                peek.left=null;
            }

        }
        return list;
    }


    //大神的想法：对前序遍历做稍微的改动，使其输出顺序为中-右-左，再反转链表，即可得到左-右-中，即后序遍历
    //额，感觉自己太傻了。。。
    public List<Integer> postorderTraversal2(TreeNode root){
        LinkedList<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root==null){
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (pop.left!=null){
                stack.push(pop.left);
            }
            if (pop.right!=null){
                stack.push(pop.right);
            }

        }
        Collections.reverse(list); //todo !!!
        return list;
    }

    //官方迭代法：也是对前序稍作改动而已
    public List<Integer> postorderTraversal3(TreeNode root){
        LinkedList<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root==null){
            return list;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            list.addFirst(pop.val); //todo !!!
            if (pop.left!=null){
                stack.push(pop.left);
            }
            if (pop.right!=null){
                stack.push(pop.right);
            }

        }
        return list;
    }
}
