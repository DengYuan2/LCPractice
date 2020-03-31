package tree;

import java.util.Stack;

// 统计左叶子节点的和 ：注意：要是左叶子节点，不是左节点
// https://leetcode-cn.com/problems/sum-of-left-leaves/
public class E_404 {
    public static void main(String[] args) {
        E_404 e = new E_404();
        General g = new General();
//        int[] arr={3,9,20,55,655,15,7,6};
        int[] arr={1,2,3,4,5};
        TreeNode node = g.buildTree(arr);
        int res1 = e.sumOfLeftLeaves1(node);
        System.out.println(res1);
        int res2 = e.sumOfLeftLeaves2(node);
        System.out.println(res2);
        int res3 = e.sumOfLeftLeaves3(node);
        System.out.println(res3);

    }

    //我的写法：看当前节点的左节点是否符合要求，符合就加上，然后递归左边节点和右边节点
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root==null){
            return 0;
        }
        int sum=0;
        if (root.left!=null){
            if (root.left.left==null&&root.left.right==null){
                sum+=root.left.val;
            }
        }
        return sum+sumOfLeftLeaves1(root.left)+sumOfLeftLeaves1(root.right);
    }

    //大神写法：其实思想和我的是差不多的
    public int sumOfLeftLeaves2(TreeNode root){
        if (root==null){
            return 0;
        }
        if (isLeaf(root.left)){
            return root.left.val+sumOfLeftLeaves2(root.right);
        }
        return sumOfLeftLeaves2(root.left)+sumOfLeftLeaves2(root.right);


    }

    public boolean isLeaf(TreeNode node){
        if (node==null){
            return false;
        }

        return node.left==null&&node.right==null;
    }

    //我自己写的迭代法：用栈；和网上的思路差不多
    public int sumOfLeftLeaves3(TreeNode root){
        if (root==null){
            return 0;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int sum=0;
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            if (pop.right!=null){
                stack.add(pop.right);
            }
            if (pop.left!=null){
                if (pop.left.left==null&&pop.left.right==null){
                    sum+=pop.left.val;
                }else {
                    stack.add(pop.left);
                }
            }
        }
        return sum;
    }

}
