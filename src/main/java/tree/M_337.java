package tree;

import java.awt.image.RasterOp;
import java.util.HashMap;

// 间隔遍历；打家劫舍Ⅲ
// https://leetcode-cn.com/problems/house-robber-iii/description/
public class M_337 {
    public static void main(String[] args) {
        M_337 m = new M_337();
        General g = new General();
        int[] arr1= {3,2,3,655,3,655,1};
        int[] arr2={3,4,5,1,3,655,1};
        TreeNode node = g.buildTree(arr2);
        int res1 = m.rob1(node);
        System.out.println(res1);
        int res2 = m.rob2(node);
        System.out.println(res2);
        int res3 = m.rob3(node);
        System.out.println(res3);

    }

    //我的写法：是通过了，但耗时很长啊，不用看了
    public int rob1(TreeNode root) {
        if (root==null){
            return 0;
        }
        int res = getWays(root, false);
        return res;
    }

    public int getWays(TreeNode root,boolean flag){ //root为当前节点，flag表示其父节点有没有被抢过
        if (root==null){
            return 0;
        }
        int res1=0;
        int res2=0;
        if (!flag){
            //父节点没被抢过，可以加上去，也可以不加上去，看哪个的结果更好一点
            res1=root.val+getWays(root.left,true)+getWays(root.right,true);
            res2=getWays(root.left,false)+getWays(root.right,false);
            return Math.max(res1,res2);

        }else {
            //父节点被抢过，那它不能被抢，于是可以告诉它的左右节点它没被抢
            res1=getWays(root.left,false);
            res2=getWays(root.right,false);
            return res1+res2;

        }
    }

    //大神的写法：
    //todo 好好看解析呀，动态规划及其优化啊啊啊
    // 这种思想的解析在该网址的方法一中：https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
    public int rob2(TreeNode root){
        if (root==null){
            return 0;
        }
        int value1=root.val;
        if (root.left!=null){
            value1+=rob2(root.left.left)+rob2(root.left.right);
        }
        if (root.right!=null){
            value1+=rob2(root.right.left)+rob2(root.right.right);
        }
        int value2=rob2(root.left)+rob2(root.right);
        return Math.max(value1,value2);
    }

    //官方网友（网址同上）对rob的优化：
    public int rob3(TreeNode root){

        HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();

        return robHelper(root,map);
    }

    public int robHelper(TreeNode root,HashMap<TreeNode,Integer> map){
        if (root==null){
            return 0;
        }
        int value1=root.val;
        if (root.left!=null){
            value1+=robHelper(root.left.left,map)+robHelper(root.left.right,map);
        }
        if (root.right!=null){
            value1+=robHelper(root.right.left,map)+robHelper(root.right.right,map);
        }
        int value2=Math.max(value1,robHelper(root.left,map)+robHelper(root.right,map));
        map.put(root,value2);
        return value2;

    }

    //对rob3的优化，没看呢。。。。。


}
