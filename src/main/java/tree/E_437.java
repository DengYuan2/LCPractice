package tree;

// 统计路径和等于一个数的路径数量
// https://leetcode-cn.com/problems/path-sum-iii/description/
public class E_437 {
    public static void main(String[] args) {
        int[] arr = {10, 5, -3, 3, 2, 655, 11, 3, -2, 655, 1};
//        int[] arr = {1,655,2,655,3,655,4,655,5};
        General g = new General();
        TreeNode node = g.buildTree(arr);
        E_437 e = new E_437();
        int res1 = e.pathSum(node, 18);//3
        System.out.println(res1);
    }

    //想法；分解：计算出根节点到下面各个节点的和，满足则+1；剩余递归即可.即递归套递归
    int count = 0;

    public int pathSum(TreeNode root, int sum) {

        if (root==null){
            return 0;
        }
        calculate(root,0,sum);
        if (root.left!=null){
            pathSum(root.left,sum);
        }
        if (root.right!=null){
            pathSum(root.right,sum);
        }
        return count;
    }


    public int calculate(TreeNode root, int cur,int sum) {
        cur=cur+root.val;
        if (cur == sum) {
            count++;
        }
        if (root.left != null) {
            calculate(root.left, cur,sum);
        }
        if (root.right != null) {
            calculate(root.right,cur,sum);
        }
        return count;
    }
}
