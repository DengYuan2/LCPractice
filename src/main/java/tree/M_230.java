package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 二叉搜索树中第K小的元素
// https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/description/
public class M_230 {
    public static void main(String[] args) {
        M_230 m = new M_230();
        General g = new General();
        int[] arr1 = {3,1,4,655,2};
        int[] arr2={5,3,6,2,4,655,655,1};
        TreeNode node = g.buildTree(arr2);
        int res1 = m.kthSmallest1(node, 1);
        System.out.println(res1);
        int res2 = m.kthSmallest2(node, 1);
        System.out.println(res2);
        int res3 = m.kthSmallest3(node, 3);
        System.out.println(res3);
    }

    // 想法1：遍历，存放到数组中，然后排序，不过我没试着这么写
    // 官网也有个类似的，但比我想得简单许多，也不用排序,用到了中序遍历（则自然排好序了）
    public int kthSmallest3(TreeNode root, int k){
        List<Integer> list= new ArrayList<Integer>();
        kthSmallestHelper(root,list);
        return list.get(k-1); //已经从小到大排好序，只要找第k-1个即可
    }

    // 可以看出是中序遍历的递归写法啊
    public void kthSmallestHelper(TreeNode node, List<Integer> list){
        if (node==null){
            return;
        }
        kthSmallestHelper(node.left,list);
        list.add(node.val);
        kthSmallestHelper(node.right,list);
    }


    // 搞了半天也不会做，看大神的两种写法
    // 写法1：中序遍历解法
    int cnt = 0;
    int res=0;
    public int kthSmallest1(TreeNode root, int k) {
        kthHelper(root,k);
        return res;
    }

    // 思想基本理解了：最小的那个肯定是在最左边，找到该节点A，它就是倒数第1，然后要找第2小的，查看A的右节点
    // 不过这是中序遍历？嗯，好像的确是中序遍历
    public void kthHelper(TreeNode root,int k){
        if (root==null){
            return;
        }
        kthHelper(root.left,k);
        cnt++;
        if (cnt==k){
            res=root.val;
            return;
        }
        kthHelper(root.right,k);
    }

    //大神的递归写法
    public int kthSmallest2(TreeNode root, int k){
       int left = count(root.left);
       if (left==k-1){
           return root.val;
       }else if (left<k-1){
           return kthSmallest2(root.right,k-left-1);
       }else {
           return kthSmallest2(root.left,k);
       }

    }

    //某棵树上的节点数量
    public int count(TreeNode node){
        if (node==null){
            return 0;
        }
        return 1+count(node.left)+count(node.right);
    }




}
