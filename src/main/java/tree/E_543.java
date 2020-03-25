package tree;

import java.util.ArrayList;
import java.util.LinkedList;

//二叉树的直径
//https://leetcode-cn.com/problems/diameter-of-binary-tree/description/
public class E_543 {
    public static void main(String[] args) {
        E_543 e = new E_543();
        //建立官方测试用例的树
        int[] arr = {4, -7, -3, 655, 655, -9, -3, 9, -7, -4, 655, 6, 655, -6, -6, 655, 655, 0, 6, 5, 655, 9, 655, 655, -1, -4, 655, 655, 655, -2}; ////null节点的值用655表示
        TreeNode node = new General().buildTree(arr);
        System.out.println(node);
        int diameter1 = e.diameterOfBinaryTree(node);
        System.out.println(diameter1);
        int diameter2 = e.diameterOfBinaryTree2(node);
        System.out.println(diameter2);
    }

    //我的思想：即根节点左右两节点高度之和,但该思想不正确，应该还要和其他节点的左右高度之和进行比较，取最高值
    // 如[4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]，最大直径根本就不经过根节点
    //虽然正确，但还是借用了maxDepth方法，过于累赘，不用看了
    int height=0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        int left =maxDepth(root.left);
        int right = maxDepth(root.right);
        height=Math.max(height,left+right);
        return height;
    }

    //求某一节点的高度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    //借鉴E_110中大神的写法. 哈哈哈，大神本题就是我这么写的
    //哈哈哈，官方也是这么写的
    int hi=0;

    public int diameterOfBinaryTree2(TreeNode root){
        getHeight(root);
        return hi;
    }

    public int getHeight(TreeNode root){
        if (root==null){
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        hi=Math.max(hi,left+right);
        return 1+Math.max(left,right);
    }
}
