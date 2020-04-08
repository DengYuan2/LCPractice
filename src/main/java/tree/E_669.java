package tree;

// 修剪二叉搜索树
// https://leetcode-cn.com/problems/trim-a-binary-search-tree/description/
public class E_669 {
    public static void main(String[] args) {
        E_669 e = new E_669();
        int[] arr={1,655,2};
        int[] arr2 ={3,0,4,655,2,655,655,1};
        int[] arr3 = {3,1,4,655,2};
        General g = new General();
        TreeNode node1 = g.buildTree(arr);
        TreeNode res1_1 = e.trimBST(node1, 2, 4);
        System.out.println(res1_1);
        TreeNode node2 = g.buildTree(arr2);
        TreeNode res1_2 = e.trimBST(node2, 1, 3);
        System.out.println(res1_2);
        TreeNode node3 = g.buildTree(arr3);
        TreeNode res1_3 = e.trimBST(node3, 3, 4);

        System.out.println(res1_3);
    }

    // 我自己做了半天都是错的，而且特别麻烦,还是看了大神的
    public TreeNode trimBST(TreeNode root, int L, int R) {
        //处理根节点
        if (root==null){
            return null;
        }

        if (root.val>R){
            return trimBST(root.left,L,R);
        }
        if (root.val<L){
            return trimBST(root.right,L,R);
        }
        root.left=trimBST(root.left,L,R);
        root.right=trimBST(root.right,L,R);
        return root;
    }




}
