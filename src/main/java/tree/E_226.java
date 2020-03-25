package tree;

import java.util.LinkedList;
import java.util.Queue;

//翻转二叉树
//https://leetcode-cn.com/problems/invert-binary-tree/description/
public class E_226 {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node2.right = node7;
        E_226 e = new E_226();
        System.out.println("反转前：");
        General g = new General();
        g.preList(node1);
        System.out.println("反转后：");
        e.invertTree(node1);
        g.preList(node1);
        System.out.println("再次反转：");
        e.invertTree2(node1);
        g.preList(node1);

    }

    //我的写法：
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //官方迭代法：看了思路先自己写的,然后又写了官方的
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //自己写的
//            TreeNode parent = queue.poll();
//            TreeNode left = parent.left;
//            TreeNode right = parent.right;
//            parent.left = right;
//            parent.right = left;
//            if (left != null) {
//                queue.offer(left);
//            }
//            if (right != null) {
//                queue.offer(right);
//            }
            //官方的,减少一个新建的变量
            TreeNode poll = queue.poll();
            TreeNode temp = poll.left; //保存一下左子树
            poll.left=poll.right;
            poll.right=temp;
            if (poll.left!=null){
                queue.offer(poll.left);
            }
            if (poll.right!=null){
                queue.offer(poll.right);
            }

        }
        return root;
    }


}
