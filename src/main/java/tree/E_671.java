package tree;

// 找出第二小的值
// https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/description/
public class E_671 {
    public static void main(String[] args) {
        E_671 e = new E_671();
//        int[] arr={2,2,5,655,655,5,7};
//        int[] arr = {2, 2, 2};
        int[] arr={1,1,3,1,1,3,4,3,1,1,1,3,8,4,8,3,3,1,6,2,1}; //好好把这个图画出来，就知道有些想法是错的了，答案是2，不是3啊
        General g = new General();
        TreeNode node = g.buildTree(arr);
        int res1 = e.findSecondMinimumValue(node);
        System.out.println(res1);
        int res2 = e.findSecondMinimumValue2(node);
        System.out.println(res2);
    }


    //我的想法1：前序遍历打出来，排序；官方也有类似的利用额外空间的想法
    //我的想法2：先确定好一个min和secMin,若遍历到的值<min，就改变min和secMin的值，若遍历到的值<secMin且>min，就改变secMin的值。
    // 关键是最开始如何确定min和secMin:min就找根节点，secMin要在后面找第一个大于它的值
    int minVal=-1;
    int secMin=-1;
    public int findSecondMinimumValue(TreeNode root) {
        if (root==null){
            return -1;
        }
        minVal=root.val;
        judge(root.left);
        judge(root.right);
        return secMin;
    }

    public void judge(TreeNode root){
        if (root==null){
            return;
        }
        if (secMin==-1){ //只针对最开始找secMin的时候
            if (root.val!=minVal){
                secMin=root.val;
            }
        }else {
            if (root.val<minVal){
                secMin=minVal;
                minVal=root.val;
            }else if (root.val<secMin&&root.val>minVal){
                secMin=root.val;
            }
        }

        judge(root.left);
        judge(root.right);

    }

    //大神的写法；有点懂他的思想，懵懵的
    public int findSecondMinimumValue2(TreeNode root){
        if (root==null ||root.left==null){ //因为只要有一个为null,则另一个必为null
            return -1;
        }
        int left = root.left.val;
        int right = root.right.val;
        if (left==root.val){
            left=findSecondMinimumValue2(root.left);
        }
        if (right==root.val){
            right=findSecondMinimumValue2(root.right);
        }
        if (left!=-1&&right!=-1){
            return Math.min(left,right);
        }
        if (left!=-1){
            return left;
        }
        return right;
    }
}
