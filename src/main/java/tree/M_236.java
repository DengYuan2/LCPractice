package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

// 二叉树的最近公共祖先 [E_235求的是；二叉搜索树的最近公共祖先]
// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
public class M_236 {
    public static void main(String[] args) {
        General g = new General();
        M_236 m = new M_236();
        int[] arr = {3, 5, 1, 6, 2, 0, 8, 655, 655, 7, 4};
        TreeNode node = g.buildTree(arr);
        TreeNode res1 = m.lowestCommonAncestor1(node, new TreeNode(5), new TreeNode(4));
        System.out.println(res1);
        TreeNode res2 = m.lowestCommonAncestor2(node, new TreeNode(5), new TreeNode(4));
        System.out.println(res2);
    }

    //我的想法：看两个节点是否在根节点两侧，是的话则就是根节点；不是的话就递归
    // 额，超时。。。也不知道到底对不对，所以还是别看了
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
//        if (root.val==p.val || root.val==q.val){
//            return root;
//        }

        //两节点都在根节点左边
        if (lowestCommonHelper(root.left, p.val) == 1 && lowestCommonHelper(root.left, q.val) == 1) {
            return lowestCommonAncestor1(root.left, p, q);
        }
        //两节点都在根节点右边
        if (lowestCommonHelper(root.right, p.val) == 1 && lowestCommonHelper(root.right, q.val) == 1) {
            return lowestCommonAncestor1(root.right, p, q);
        }
        //两节点在根节点两侧，或者其中一个节点就是根节点
        return root;

    }

    // 看这棵树上是否有要找的节点：有则为1
    public int lowestCommonHelper(TreeNode root, int value) {
        if (root == null) {
            return 0;
        }
        if (root.val == value) {
            return 1;
        }
        // 按理说，要么在左边找到，要么在右边找到，最大值只会为1
        return lowestCommonHelper(root.left, value) + lowestCommonHelper(root.right, value);

    }

    //大神写法，跟官方递归法思想好像是一样的，但我还是不太理解
    //该思想的解释：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/c-jing-dian-di-gui-si-lu-fei-chang-hao-li-jie-shi-/
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //注：他们的p节点和q节点就是树中的，可以直接用root==p，不过我测试时是new了两个，所以要用值来比较。
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    // 官方迭代法；https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetcod/
    // 注：他们的p节点和q节点就是树中的，可以直接用root==p，所以下面的while循环可以这么写
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
        stack.push(root);
        map.put(root, null);
        while (!map.containsKey(p) || !map.containsKey(q)) {
            TreeNode pop = stack.pop();
            if (pop.right != null) {
                stack.push(pop.right);
                map.put(pop.right, pop);
            }
            if (pop.left != null) {
                stack.push(pop.left);
                map.put(pop.left, pop);
            }

        }

        // 将p的所有父节点放入集合中，先放进去的离得最近
        HashSet<TreeNode> set = new HashSet<TreeNode>();
        while (p != null) {
            set.add(p);
            p = map.get(p);
        }

        while (!set.contains(q)) {
            q = map.get(q);
        }
        return q;
    }

    //官方另一种解法，没看。。。。

}

