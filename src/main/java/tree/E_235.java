package tree;
// 二叉搜索树的最近公共祖先
// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
public class E_235 {
    public static void main(String[] args) {
        E_235 e = new E_235();
        General g = new General();
        int[] arr={6,2,8,0,4,7,9,655,655,3,5};
        int[] arr2={2,1};
        TreeNode node1 = g.buildTree(arr);
        TreeNode node2 = g.buildTree(arr2);
        TreeNode res1_1 = e.lowestCommonAncestor1(node1, new TreeNode(0), new TreeNode(4));
        System.out.println(res1_1);
        TreeNode res1_2 = e.lowestCommonAncestor1(node2, new TreeNode(2), new TreeNode(1));
        System.out.println(res1_2);
        TreeNode res2_1 = e.lowestCommonAncestor2(node1, new TreeNode(0), new TreeNode(4));
        System.out.println(res2_1);
        TreeNode res2_2 = e.lowestCommonAncestor2(node2, new TreeNode(2), new TreeNode(1));
        System.out.println(res2_2);
        TreeNode res3_1 = e.lowestCommonAncestor2(node1, new TreeNode(0), new TreeNode(4));
        System.out.println(res3_1);
        TreeNode res3_2 = e.lowestCommonAncestor2(node2, new TreeNode(2), new TreeNode(1));
        System.out.println(res3_2);
    }

    //我的写法
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null){
            return null;
        }
        int left = p.val<=q.val?p.val:q.val;
        int right = p.val>q.val?p.val:q.val;
        if (root.val<=right&&root.val>=left){
            return root;
        }
        if (root.val>right){
            return lowestCommonAncestor1(root.left,p,q);
        }
        return lowestCommonAncestor1(root.right,p,q);
    }
    
    //大神的写法，思路和我一致，但写法简洁许多;官方的递归也是如此
    public TreeNode lowestCommonAncestor2(TreeNode root,TreeNode p,TreeNode q){
        if (root.val<p.val&&root.val<q.val){
            return lowestCommonAncestor2(root.right,p,q);
        }
        if (root.val>p.val&&root.val>q.val){
            return lowestCommonAncestor2(root.left,p,q);
        }
        return root;
    }

    //官方迭代
    public TreeNode lowestCommonAncestor3(TreeNode root,TreeNode p,TreeNode q){
        TreeNode node = root;
        while (node!=null){
            if (p.val<node.val&&q.val<node.val){
                node=node.left;
            }else if (p.val>node.val&&q.val>node.val){
                node=node.right;
            }else {
                return node;
            }
        }
        return null;

    }


}
