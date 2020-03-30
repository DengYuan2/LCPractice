package tree;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Stack;

// 二叉树的最小深度
// https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/description/
public class E_111 {
    public static void main(String[] args) {
        E_111 e = new E_111();
        General g = new General();
//        int[] arr = {3, 9, 20, 655, 655, 15, 7};
        int[] arr = {1, 2, 3, 4, 655, 655, 7, 8};
//        int[] arr = {1, 2, 655, 4, 655, 5, 655, 6};
        TreeNode node = g.buildTree(arr);
        int res1 = e.minDepth1(node);
        System.out.println(res1);
        int res2 = e.minDepth2(node);
        System.out.println(res2);
        int res3 = e.minDepth3(node);
        System.out.println(res3);
        int res4 = e.minDepth4(node);
        System.out.println(res4);
    }

    //我的想法1：和求树的高度一致，额，结果错了，比如1,2结果会是1，但应该是2
    //改改改。。。终于改好了:不过我其实没什么思想，只是这样改改，觉得差不多了
    //官网网友给出了这样做的思想，虽然比我写的简单： https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/li-jie-zhe-dao-ti-de-jie-shu-tiao-jian-by-user7208/
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.right == null && root.left == null) {
            return 1;
        }

        if (root.left == null) {
            return 1 + minDepth1(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth1(root.left);
        }

        return 1 + Math.min(minDepth1(root.left), minDepth1(root.right));


    }

    //我的想法2：与层次遍历求高度的思路类似，只要中途停止即可
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int layer = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            layer++;
            count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode poll = queue.poll();
                if (poll.right == null && poll.left == null) {
                    return layer;
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return layer;
    }

    //大神的递归，虽然跟我差不多，但更简单，而且的确是用了求树的高度的思想
    public int minDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth3(root.left);
        int right = minDepth3(root.right);
        if (left == 0 || right == 0) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;


    }

    // 官方深度遍历方法：需要遍历所有节点，不像层次遍历那样可以直接停止
    // https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/er-cha-shu-de-zui-xiao-shen-du-by-leetcode/
    public int minDepth4(TreeNode root){
        if (root==null){
            return 0;
        }
        Stack<Pair<TreeNode,Integer>> stack = new Stack<Pair<TreeNode,Integer>>();
        stack.add(new Pair<TreeNode, Integer>(root,1));
        int min=Integer.MAX_VALUE;
        int count = 0;
        while (!stack.isEmpty()){
            Pair<TreeNode,Integer> pair = stack.pop();
            TreeNode key = pair.getKey();
            count =  pair.getValue();
            if (key.left==null&&key.right==null){
                min=Math.min(min,count);
            }
            if (key.left!=null){
                stack.add(new Pair<TreeNode, Integer>(key.left,count+1));
            }
            if (key.right!=null){
                stack.add(new Pair<TreeNode, Integer>(key.right,count+1));
            }
        }
        return min;
    }

}
