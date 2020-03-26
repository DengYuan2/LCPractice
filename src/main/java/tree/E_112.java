package tree;

import java.util.Stack;

// 判断路径和是否等于一个数
// https://leetcode-cn.com/problems/path-sum/
public class E_112 {
    public static void main(String[] args) {
        E_112 e = new E_112();

//        int[] arr = {5, 4, 8, 11, 655, 13, 4, 7, 2, 655, 655, 655, 1};//655表示null
        int[] arr = {1,2};//655表示null
//        int[] arr = {1,2,655,3,655,4,655,5};//655表示null
        General g = new General();
        TreeNode node = g.buildTree(arr);
        boolean res1 = e.hasPathSum1(node, 1);
        System.out.println(res1);
        boolean res2 = e.hasPathSum2(node, 1);
        System.out.println(res2);
        boolean res3 = e.hasPathSum3(node, 1);
        System.out.println(res3);
        boolean res4 = e.hasPathSum4(node, 1);
        System.out.println(res4);
        boolean res5 = e.hasPathSum5(node, 1);
        System.out.println(res5);
    }

    //我的写法: 注意理解题意，一定要是子节点到根节点哈
    //写法有点烂啊
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return judge(root, 0, sum);

    }

    public boolean judge(TreeNode root, int val, int sum) {
//        if (root == null) {
//            return false;
//        }
//        boolean flag = false;
//        val = root.val + val;
//        if (root.left == null && root.right == null && val == sum) {
//            flag = true;
//        }
//        if (!flag) {
//            flag = judge(root.left, val, sum);
//        }
//        if (!flag) {
//            flag = judge(root.right, val, sum);
//        }
//        return flag;

        //看网友的，可以改成如下，更为简洁
        if (root==null){
            return false;
        }
        val=val+root.val;
        if (root.left==null&&root.right==null){
            return val==sum;
        }else {
            return judge(root.left,val,sum)||judge(root.right,val,sum);
        }

    }

    //大神的写法,充分利用了sum啊；和官方的递归方法思路类似
    //我是想加上是否等于sum（sum不变），而大神是想减去是否等于sum(sum在变)
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);

    }

    //官方递归
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        sum -= root.val;
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSum3(root.left, sum) || hasPathSum3(root.right, sum);
    }

    //迭代法:在上面两个的基础上自己写的（相当于是先有了思路）
    public boolean hasPathSum4(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.left == null && pop.right == null && sum == pop.val) {
                return true;
            }
            if (pop.right != null) {
                pop.right.val += pop.val; //直接让左右节点的值为 到这里的和
                stack.push(pop.right);
            }
            if (pop.left != null) {
                pop.left.val += pop.val;
                stack.push(pop.left);
            }
        }
        return false;
    }

    //官方迭代法：一个栈记录节点，一个栈记录对应节点还剩下的数（已减去该节点的值）
    public boolean hasPathSum5(TreeNode root,int sum){
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        Stack<Integer> numStack = new Stack<Integer>();
        nodeStack.push(root);
        numStack.push(sum-root.val);
        while (!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            Integer num = numStack.pop();
            if (node.left==null&&node.right==null&&num==0){
                return true;
            }
            if (node.right!=null){
                nodeStack.push(node.right);
                numStack.push(num-node.right.val);
            }
            if (node.left!=null){
             nodeStack.push(node.left);
             numStack.push(num-node.left.val);
            }
        }
        return false;
    }

}
