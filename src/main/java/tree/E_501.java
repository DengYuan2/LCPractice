package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）
// 进阶：不适用额外的空间
// https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/description/
public class E_501 {
    public static void main(String[] args) {
        E_501 e = new E_501();
//        int[] arr={1,655,2,2};
        int[] arr={1,1,2,655,655,2};
        General g = new General();
        TreeNode node = g.buildTree(arr);
        int[] res1 = e.findMode1(node);
        System.out.println(Arrays.toString(res1));
        int[] res2 = e.findMode2(node);
        System.out.println(Arrays.toString(res2));

    }

    // 我的想法：借用ArrayList,有更大的众数则替换（清除整个list后再加上），相等则直接在list后加上，少则不作为
    // 额，我的方法好像太垃圾了
    List<Integer> list =new ArrayList<Integer>();
    int count = 1;
    int max = count;
    TreeNode pre = null;
    public int[] findMode1(TreeNode root) {
        findHelper(root);
        // 针对最后一个
        if (pre!=null){
            if (count>max){
                list.removeAll(list);
                list.add(pre.val);
                max=count;
            }else if (count==max){
                list.add(pre.val);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }
        return  res;
}

    // 用中序遍历的方式
    public void findHelper(TreeNode root){
        if (root==null){
            return;
        }
        if (root.left!=null){
            findHelper(root.left);
        }
        if (pre!=null){
            if (pre.val==root.val){
                count++;
            }else {
                if (count>max){
                    list.removeAll(list);
                    list.add(pre.val);
                    max=count;
                }else if (count==max){
                    list.add(pre.val);
                }
                count=1;
            }
        }
        pre=root;

        if (root.right!=null){
            findHelper(root.right);
        }

    }


    //嘿嘿，大神的思路和我差不多，但处理的比我更简洁(尤其是对最后一个节点的处理，因为我在58行只是count++，没有处理最后一个节点和前一个节点相同的情况，而是在调用findHelper方法后再判断一次)，效率更高欸
    // 在某些地方还是有一些差距的，看下面的注释吧
    int curCount = 1;
    int maxCount = curCount;
    TreeNode preNode  = null;
    public int[] findMode2(TreeNode root){

        ArrayList<Integer> list = new ArrayList<Integer>();
        findModeHelper(root,list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }
        return res;
    }

    public void findModeHelper(TreeNode node,List<Integer> list){
        if (node==null){
            return;
        }
        findModeHelper(node.left,list);
        if (preNode!=null){
            if (preNode.val==node.val){
                curCount++;
            }else{
                curCount=1;
            }
        }
        // 和我的不同是：比如说是1 2 2 2 3 ，我是数到3个2才在list中去更新;而大神是；当有第2个2时，就更新，有第3个2时又再更新
        // 所以大神已知处理的是当前节点，而我是遇到不等时，就处理前一个节点
        if (curCount>maxCount){
            list.clear();
            list.add(node.val);
            maxCount=curCount;
        }else if (curCount==maxCount){
            list.add(node.val);
        }
        preNode=node;
        findModeHelper(node.right,list);
    }

}
