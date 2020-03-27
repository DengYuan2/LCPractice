package tree;

// 另一个树的子树
// https://leetcode-cn.com/problems/subtree-of-another-tree/description/
public class E_572 {
    public static void main(String[] args) {
        E_572 e = new E_572();
        General g = new General();
        int[] s= {3,4,5,1,2};
        int[] t={4,1,2};
        TreeNode tree1 = g.buildTree(s);
        TreeNode tree2 = g.buildTree(t);
        e.isSubtree(tree1,tree2);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {

        return false;
    }
}
