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
        int[] arr3 = {1, 0};
        TreeNode tree = g.buildTree(arr2);
        boolean res1 = e.isSymmetric1(tree);
        System.out.println(res1);
        boolean res2 = e.isSymmetric2(tree);
        System.out.println(res2);
        boolean res3 = e.isSymmetric3(tree);
        System.out.println(res3);
        boolean res4 = e.isSymmetric4(tree);
        System.out.println(res4);
    }

    //我的递归写法：过于复杂，虽然正确，但没有必要啊；别看了吧
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        if (root.left.val != root.right.val) {
            return false;
        }
        //如果有左右两个节点，且节点值相等，则两颗树递归比较
        return whatEver(root.left, root.right);

    }

    //从根节点的左右两个节点开始，比较两个小三角是否对称，并逐渐递归
    public boolean whatEver(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        boolean res = judge(t1, t2);
        if (res) {
            res = whatEver(t1.left, t2.right);
        }
        if (res) {
            res = whatEver(t1.right, t2.left);
        }
        return res; //其实可以让三个用&&取交集，但我觉得还是判断一下比较好，可以减少不必要的递归次数。
    }

    //判断两颗三角小树是否对称
    public boolean judge(TreeNode t1, TreeNode t2) {
        return isEqual(t1.left, t2.right) && isEqual(t1.right, t2.left);

    }

    //判断两个节点是否相等
    public boolean isEqual(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 != null && t2 != null && t1.val == t2.val) {
            return true;
        }
        return false;
    }

    //大神方法,和官方递归方法一致
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric3(root.left,root.right);
    }

    public boolean isSymmetric3(TreeNode t1,TreeNode t2){
        if (t1==null&&t2==null){
            return true;
        }
        if (t1==null||t2==null){
            return false;
        }
        if (t1.val==t2.val){
            return false;
        }
        return isSymmetric3(t1.left,t2.right)&&isSymmetric3(t1.right,t2.left);
    }

    //官方迭代方法
    //思想：https://leetcode-cn.com/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode/
    public boolean isSymmetric4(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1==null&&t2==null){
                continue;
            }
            if (t1==null||t2==null){
                return false;
            }
            if (t1.val!=t2.val){
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }



    //我的想法：一个以左中右遍历，一个以右中左遍历，看结果是否相同
    //额，好像不是递归吧
    //算是借鉴了E_572中官方的先序遍历思想。但这既不是递归，也不是迭代啊
    public boolean isSymmetric2(TreeNode root) {
        String s1 = preList1(root, true);
        String s2 = preList2(root, true);
        System.out.println(s1);
        System.out.println(s2);
        return s1.equals(s2);
    }

    public String preList1(TreeNode node, boolean left) {
        if (node == null) {
            if (left) {
                return "l";
            } else {
                return "r";
            }
        }
        return "#" + node.val + preList1(node.left, true) + preList1(node.right, false);

    }

    public String preList2(TreeNode node, boolean right) {
        if (node == null) {
            if (right) {
                return "l";
            } else {
                return "r";
            }
        }
        return "#" + node.val + preList2(node.right, true) + preList2(node.left, false);

    }

}
