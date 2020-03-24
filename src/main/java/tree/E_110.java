package tree;

// 判断某树是否是平衡二叉树
// https://leetcode-cn.com/problems/balanced-binary-tree/
public class E_110 {
    public static void main(String[] args) {
        E_110 e = new E_110();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
//        node1.left = node2;
        node1.right = node3;
//        node2.left = node4;
//        node3.left = node5;
        node3.right = node6;
//        node2.right = node7;
        boolean b1 = e.isBalanced1(node1);
        System.out.println(b1);
        boolean b2 = e.isBalanced2(node1);
        System.out.println(b2);
        boolean e3 = e.isBalanced3(node1);
        System.out.println(e3);
        boolean b4 = e.isBalanced4(node1);
        System.out.println(b4);
    }


    //借助E_104中（已将方法抄到下面）节点的高度
    //算是自顶向下的递归
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean flag = true;
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) {
            return false;
        }
        if (root.left != null) {
            flag = isBalanced1(root.left);
        }
        if (flag) { //如果左边已经判断是不平衡的了，右边就不用再判断了
            if (root.right != null) {
                flag = isBalanced1(root.right);
            }
        }
        return flag;
    }

    //求某一节点的高度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //官方自顶向下的递归（也用了maxDepth方法）,写法更为简单
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(maxDepth(root.right) - maxDepth(root.left)) < 2
                && isBalanced2(root.left)
                && isBalanced2(root.right);
    }

    //官方自底向上的递归
    //解析：https://leetcode-cn.com/problems/balanced-binary-tree/solution/balanced-binary-tree-di-gui-fang-fa-by-jin40789108/
    public boolean isBalanced3(TreeNode root) {
        return recur(root) != -1;
    }

    public int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        //判断该节点是否平衡，不平衡直接结束此方法，下同
        if (left == -1) {
            return -1;
        }
        int right = recur(root.right);
        if (right == -1) {
            return -1;
        }
        //平衡则返回该节点的高度，否则返回-1；
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;


    }

    //大神巧妙地利用求节点高度的过程,一边求高度一遍比较
    //也是自底向上的递归，与方法三相比：没有判断是否已经不平衡从而提早结束；但其实稍微改一下也是能达到一样效果的（不过我没改，嘿嘿）
    boolean result = true;

    public boolean isBalanced4(TreeNode root) {
        maxDep(root);
        return result;
    }

    public int maxDep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDep(root.left);
        int right = maxDep(root.right);
        if (Math.abs(left - right) > 1) {
            result = false;
        }
        return Math.max(left, right) + 1;
    }

}
