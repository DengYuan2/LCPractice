package tree;

import java.util.ArrayList;
import java.util.LinkedList;

//存放一些公用的方法
public class General {

    //为了建立官方测试案例的树 [注：null值此处都用655（数组中）代替]
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
        while (!list.isEmpty()) {
            TreeNode root = linkedList.remove(0);
            TreeNode left = null;
            if (!list.isEmpty()) {
                left = list.remove(0);

            }
            TreeNode right = null;
            if (!list.isEmpty()) {
                right = list.remove(0);
            }
            root.left = left;
            root.right = right;
            if (left != null) {
                linkedList.addLast(left);
            }
            if (right != null) {
                linkedList.addLast(right);
            }
        }
        return first;

    }

    //前序遍历
    public void preList(TreeNode root) {
        System.out.println(root);
        if (root.left!=null){
            preList(root.left);
        }
        if (root.right!=null){
            preList(root.right);
        }

    }
}
