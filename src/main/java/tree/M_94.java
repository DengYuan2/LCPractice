package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 二叉树的中序遍历（要求非递归）
// https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
public class M_94 {
    public static void main(String[] args) {
        M_94 m = new M_94();
        General g = new General();
        int[] arr={1,655,2,3};
        int[] arr2={1,2,3,4,5,6,7};
        TreeNode node = g.buildTree(arr2);
//        List<Integer> res1 = m.inorderTraversal1(node);
//        System.out.println(res1);
        List<Integer> res2 = m.inorderTraversal2(node);
        System.out.println(res2);


    }

    /**
     *           1
     *     2           3
     *  4     5     6           //    4 2 5 1 6 3 7
     *          6 3  // 4 2 5 1
     *
     *      2 5     1 3        4
     */
    // 和我在H_145最开始的思路一样，当前节点的左右节点为空时才将其值加入到list中
    // 同样的问题：改变了原来的结构
    // 稍显垃圾
    public List<Integer> inorderTraversal1(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root==null){
            return list;
        }

        if (root.right!=null){
            stack.push(root.right);
            root.right=null;
        }
        stack.push(root);
        if (root.left!=null){
            stack.push(root.left);
            root.left=null;
        }
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            if (pop==null){
                continue;
            }
            if (pop.right==null&&pop.left==null){
                list.add(pop.val);
                continue;
            }
            if (pop.right!=null){
                stack.push(pop.right);
                pop.right=null;
            }
            stack.push(pop);
            if (pop.left!=null){
                stack.push(pop.left);
                pop.left=null;
            }

        }



        return list;

    }

    // 大神的写法和官方的迭代方法相同：将递归中序遍历用栈表示
    // 能看明白，但自己写不出来
    public List<Integer> inorderTraversal2(TreeNode root){
        LinkedList<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur=root;
        while (cur!=null || !stack.isEmpty()){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur=cur.right;
        }
        return list;

    }

    //官方莫里斯遍历方法，没有看。。。。


}
