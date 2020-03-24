package tree;

import java.util.ArrayList;
import java.util.LinkedList;

//二叉树的直径
//https://leetcode-cn.com/problems/diameter-of-binary-tree/description/
public class E_543 {
    public static void main(String[] args) {
        E_543 e = new E_543();
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
//        int diameter = e.diameterOfBinaryTree(node1);
//        System.out.println(diameter);

        int[] arr = {4, -7, -3, 655, 655, -9, -3, 9, -7, -4, 655, 6, 655, -6, -6, 655, 655, 0, 6, 5, 655, 9, 655, 655, -1, -4, 655, 655, 655, -2};
        TreeNode node = e.buildTree(arr);
        System.out.println(node);
        int i = e.diameterOfBinaryTree(node);
        System.out.println(i);
    }

    //我的思想：即根节点左右两节点高度之和,但该思想不正确，应该还要和其他节点的左右高度之和进行比较，取最高值
    // 如[4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]，最大直径根本就不经过根节点
    int height=0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        int left =maxDepth(root.left);
        int right = maxDepth(root.right);
        height=Math.max(height,left+right);

        return height;
    }

    //求某一节点的高度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public TreeNode buildTree(int[] arr) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        for (int i = 0; i < arr.length; i++) {
            TreeNode node = null;
            if (arr[i] < 655) {
                node = new TreeNode(arr[i]);
            } else {
                node = null;
            }
            list.add(node);
        }
        LinkedList<TreeNode> linkedList = new LinkedList<TreeNode>();
        TreeNode first = list.remove(0);
        linkedList.addLast(first);
        while (!list.isEmpty()){
            TreeNode root=linkedList.remove(0);
            TreeNode left=null;
            if (!list.isEmpty()){
                left = list.remove(0);

            }
            TreeNode right= null;
            if (!list.isEmpty()){
                right= list.remove(0);
            }
            root.left=left;
            root.right=right;
            if (left!=null){
                linkedList.addLast(left);
            }
            if (right!=null){
                linkedList.addLast(right);
            }
        }
        return first;

    }
}
