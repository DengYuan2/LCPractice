package tree;

import linkedList.ListNode;

// 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树
// https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/description/
public class E_108 {
    public static void main(String[] args) {
        E_108 e = new E_108();
//        int[] arr={-10, -3, 0, 5, 9};
        int[] arr={0,1,2,3,4,5,6,7};
        TreeNode res1 = e.sortedArrayToBST(arr);
        System.out.println(res1);
        /**
         * 官方重要的知识点；
         *下面是一些关于 BST 的知识。
         * 中序遍历不能唯一确定一棵二叉搜索树。
         * 先序和后序遍历不能唯一确定一棵二叉搜索树。
         * 先序/后序遍历和中序遍历的关系：
         * inorder = sorted(postorder) = sorted(preorder)，
         * 中序+后序、中序+先序可以唯一确定一棵二叉树。
         *
         */

        /**
         * 对于偶数个数的数组，要么选择中间位置左边的元素作为根节点，
         * 要么选择中间位置右边的元素作为根节点，不同的选择方案会创建不同的平衡二叉搜索树。
         */
    }

    //额，我的最初（错误的）思路是中间的为根节点，左边的就全放在左节点，右边的就全往右放。就是变成人字形。额，不符题意啊，因为就不是平衡的啦。
    //嘿嘿，这是我后来写的。和大神的基本一致，只是在sortedHelper方法中多了了三行，见下面
    public TreeNode sortedArrayToBST(int[] nums) {

        return sortedHelper(nums,0,nums.length-1);
    }

    public TreeNode sortedHelper(int[] nums,int left,int right){
        if (left>right){
            return null;
        }
        //大神没有写这三行,的确可以不要
//        if (left==right){
//            return new TreeNode(nums[left]);
//        }
        int mid = (left+right)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left=sortedHelper(nums,left,mid-1);
        node.right=sortedHelper(nums,mid+1,right);
        return node;

    }

    // 官方的思路也是如此，写出了左、右、随机这三种方法，那个随机的很有趣哦，值得一看
    // https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-15/

}
