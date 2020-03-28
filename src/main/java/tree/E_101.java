package tree;

import java.util.LinkedList;

// 树的对称，要求用递归和迭代两种方式
// https://leetcode-cn.com/problems/symmetric-tree/description/
public class E_101 {
    public static void main(String[] args) {
        E_101 e = new E_101();
        General g = new General();
        int[] arr = {1, 2, 2, 3, 4, 4, 3};
        int[] arr2 = {1, 2, 2, 655, 3, 655, 3};
        TreeNode tree = g.buildTree(arr);
//        boolean res1 = e.isSymmetric1(tree);
//        System.out.println(res1);
        boolean res2 = e.isSymmetric2(tree);
        System.out.println(res2);
    }

    //我的写法之迭代：用层次遍历，思路暂时不行
//    public boolean isSymmetric1(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//
//        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
//        if (root.left != null) {
//            queue.add(root.left);
//        }
//        if (root.right != null) {
//            queue.add(root.right);
//        }
//        queue.add(root);
//
//        while (!queue.isEmpty()) {
//            if (queue.size() % 2 != 0) {
//                return false;
//            }
//            while (queue.size() > 0) {
//                TreeNode poll = queue.poll();
//                if (poll.left != null) {
//                    queue.addLast(poll.left);
//                }
//                if (poll.right != null) {
//                    queue.addLast(poll.right);
//                }
//            }
//
//        }
//
//    }

    //我的写法之递归：一个以左中右遍历，一个以右中左遍历，看结果是否相同
    //额，好像不是递归吧
    //算是借鉴了E_572中官方的先序遍历思想。但这既不是递归，也不是迭代啊
    public boolean isSymmetric2(TreeNode root) {
        String s1=preList1(root,true);
        String s2=preList2(root,true);
        System.out.println(s1);
        System.out.println(s2);
        return s1.equals(s2);
    }

    public String preList1(TreeNode node,boolean left) {
        if (node == null) {
            if (left){
                return "l";
            }else {
                return "r";
            }
        }
        return "#" + node.val + preList1(node.left,true) + preList1(node.right,false);

    }

    public String preList2(TreeNode node,boolean right) {
        if (node == null) {
            if (right){
                return "l";
            }else {
                return "r";
            }
        }
        return "#" + node.val + preList2(node.right,true) + preList2(node.left,false);

    }

}
