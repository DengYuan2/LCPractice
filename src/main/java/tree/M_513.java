package tree;

import java.util.LinkedList;

// 给定一个二叉树，在树的最后一行找到最左边的值。注；并不一定是左节点，只要是最后一层的第一个就行
// https://leetcode-cn.com/problems/find-bottom-left-tree-value/description/
public class M_513 {
    public static void main(String[] args) {
        M_513 m = new M_513();
        General g = new General();
        int[] arr={2,1,3}; //1
        int[] arr2={1,2,3,4,5,6,655,655,655,655,655,7}; //7
        TreeNode node = g.buildTree(arr2);
        int res1 = m.findBottomLeftValue(node);
        System.out.println(res1);
        int res2 = m.findBottomLeftValue2(node);
        System.out.println(res2);
        int res3 = m.findBottomLeftValue3(node);
        System.out.println(res3);
    }

    //我的想法：层次遍历，并记录下每层的最左边
    public int findBottomLeftValue(TreeNode root) { //已加点给根节点不为null
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int first=-1; //记录这一层的第一个的值
        int count=0; //记录每层的个数
        TreeNode node = null; //记录从队列中poll出的节点
        while (!queue.isEmpty()){
            count=queue.size();
            for (int i = 0; i < count; i++) {
                node=queue.poll();
                if (i==0){
                    first=node.val;
                }
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        return first;
    }

    //大神写法 [且大神将其视为简单题]
    //每一层都从右向左看（其实就没有层的区别了），大概能理解
    public int findBottomLeftValue2(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        TreeNode node = null;
        while (!queue.isEmpty()){
            node= queue.poll();
            if (node.right!=null){
                queue.offer(node.right);
            }
            if (node.left!=null){
                queue.offer(node.left);
            }
        }
        return node.val;
    }

    //官方网友的深度优先遍历：记录深度最大的第一个节点 [单看这个思想，我写不出下面这么简单的代码]
    //其实想法很简单啊，但我为社么不能想出来啊~~
    int[] result={0,-1}; //前一个为节点值，后一个为深度
    public int findBottomLeftValue3(TreeNode root){

        findBottomHelper(root,0);
        return result[0];
    }

    public void findBottomHelper(TreeNode root,int depth){
        if (root==null){
            return;
        }
        if (depth>result[1]){ //当前节点所处深度大于已知深度时，就保存该节点的值
            result[0]=root.val;
            result[1]=depth;
        }
        findBottomHelper(root.left,depth+1);
        findBottomHelper(root.right,depth+1);

    }

}
