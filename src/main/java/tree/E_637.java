package tree;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 二叉树的层平均值
// https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
public class E_637 {
    public static void main(String[] args) {
        E_637 e = new E_637();
        General g = new General();
        int[] arr={3,9,20,655,655,15,7};
        //下面这棵树，用General类中的方法不太好创建（因为这里数据很大，不过其实我也只要改个数字就好了），故手动创建
        TreeNode root = new TreeNode(2147483647);
        TreeNode left= new TreeNode(2147483647);
        TreeNode right = new TreeNode(2147483647);
        root.left=left;
        root.right=right;

        TreeNode node = g.buildTree(arr);
        List<Double> res1 = e.averageOfLevels(node);
        System.out.println(res1);
        List<Double> res1_1 = e.averageOfLevels(root);
        System.out.println(res1_1);
        List<Double> res2 = e.averageOfLevels2(root);
        System.out.println(res2);

    }

    //我的写法：和大神的一致，比官方解答的广度优先遍历要简单些，占用更少的内存
    public List<Double> averageOfLevels(TreeNode root) {
        if (root==null){
            return null;
        }
        ArrayList<Double> list = new ArrayList<Double>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        double sum=0; //!!!要用double接收
        int count=0;
        TreeNode cur=null;
        while (!queue.isEmpty()){
            sum=0;
            count=queue.size();
            for (int i = 0; i < count; i++) {
                cur=queue.poll();
                sum+=cur.val; //此处的值可能超过int范围，故考虑用double接收
                if (cur.left!=null){
                    queue.add(cur.left);
                }
                if (cur.right!=null){
                    queue.add(cur.right);
                }
            }
            list.add(sum/count);
        }
        return list;
    }

    //官方深度优先
    //思想：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/solution/er-cha-shu-de-ceng-ping-jun-zhi-by-leetcode/
    public List<Double> averageOfLevels2(TreeNode root){
        if (root==null){
            return null;
        }
        ArrayList<Double> res = new ArrayList<Double>();
        ArrayList<Integer> count = new ArrayList<Integer>();
        averageHelper(new Pair<TreeNode,Integer>(root,0),res,count);
        for (int i = 0; i < res.size(); i++) {
            res.set(i,res.get(i)/count.get(i));
        }
        return res;

    }

    public void averageHelper(Pair<TreeNode, Integer> pair,ArrayList<Double> sum,ArrayList<Integer> count){
        TreeNode node = pair.getKey();
        Integer layer = pair.getValue();
        if (sum.size()<=layer){ //如果这层的数据还一个没加过
            sum.add(node.val*1.0); //int变成double的巧妙做法！！
            count.add(1);
        }else { //如果已经处理过这层的数据
            sum.set(layer,sum.get(layer)+node.val); //todo list有set方法！我知道的太少了吧
            count.set(layer,count.get(layer)+1);
        }

        if (node.left!=null){
            averageHelper(new Pair<TreeNode,Integer>(node.left,layer+1),sum,count);
        }
        if (node.right!=null){
            averageHelper(new Pair<TreeNode,Integer>(node.right,layer+1),sum,count);
        }

    }
}
