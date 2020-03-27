package tree;


import javafx.util.Pair;
import linkedList.ListNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//数的高度
public class E_104 {
    public static void main(String[] args) {
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
        E_104 e = new E_104();
        int i1 = e.maxDepth(node1);
        System.out.println(i1);
        int i2 = e.maxDepth2(node1);
        System.out.println(i2);
        int i3 = e.maxDepth3(node1);
        System.out.println(i3);
        System.out.println();
        e.list(node1);
    }

    //学习数据结构时老师讲过，所以会写
    //但递归方法的缺点不要忘记:如果层级过深，保存过多临时变量，容易导致栈溢出
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //官方：利用栈将递归转化成迭代,DFS
    public int maxDepth2(TreeNode root) { // 3 6
        if (root == null) {
            return 0;
        }
        Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();
        Pair<TreeNode, Integer> pair = new Pair<TreeNode, Integer>(root, 1);
        stack.push(pair);
        int height=0;
        while (!stack.isEmpty()){
            Pair<TreeNode, Integer> pop = stack.pop();
            height=Math.max(height,pop.getValue());
            if (pop.getKey().right!=null){
                Pair<TreeNode, Integer> right = new Pair<TreeNode, Integer>(pop.getKey().right, pop.getValue() + 1);
                stack.add(right);
            }
            if (pop.getKey().left!=null){
                Pair<TreeNode, Integer> left = new Pair<TreeNode, Integer>(pop.getKey().left, pop.getValue() + 1);
                stack.add(left);
            }
        }
        return height;

    }

    //网友：借助队列，使用层序遍历的方式。BFS:层次遍历，每次都扫一层
    // 思路：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/marveljian-dan-de-xue-xi-bi-ji-104-by-marvel_ty/
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int height = 0; //记录层数，即高度
        while (queue.size() > 0) {
            height++;
            for (int i = 0; i < queue.size(); i++) { //queue.size()即为每层的节点数啊
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

        }
        return height;
    }

    //补充：中序遍历
    public void list(TreeNode root){
        if (root==null){
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.println(node);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
    }

}
