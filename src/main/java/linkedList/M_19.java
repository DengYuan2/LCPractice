package linkedList;

import sun.plugin.javascript.navig.LinkArray;

import java.util.LinkedList;

//删除链表的倒数第N个节点（给定的n保准有效）:使用一趟扫描实现
//https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
public class M_19 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        ListNode listNode = removeNthFromEnd(n1, 6);
        System.out.println(listNode);
    }

    //关键是找到倒数第n个节点，若是不要求一趟扫描完成，我会先遍历一遍获得链表长度length,再计数找第length-n个节点,然后再处理删除
    //根据别人的思路：双指针
    //对哦，一个指针搞不定，就用了两个指针啊
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
//        int count=1;
//        ListNode pre =null; //要删除的节点的前面一个节点
//        ListNode left=head; //left先不动，等right和left之间的距离有n(其实是n-1)了，left才开始动，目的是使right到最后一个节点，则left自然为倒数第n个节点
//        ListNode right=head;
//        while (count<n){ //因为n保证有效，所以right肯定能走到第n个节点 例：若n=2，此处right应该走到2节点
//            right=right.next;
//            count++;
//        }
//        while (right.next!=null){ //例，若链表长度为3.则left应该是2,pre为1
//            right=right.next;
//            pre=left;
//            left=left.next;
//        }
//
//        if (pre==null){ //如果要删除的节点就是第一个节点
//            head=head.next;
//        }else {
//            pre.next=left.next;
//        }
//        return head;

        //感觉上面因为有个pre，多以多了很多次pre=left的操作，
        //完全可以直接找倒数第n+1个节点(如果有的话)
        //所以可以改成下面这样
        //该方法似乎比下面的官方方法要快，但下面的哑节点真的很巧妙
        int count =0 ;
        ListNode left=head;
        ListNode right=head;
        while (count<n){
            right=right.next;
            count++;
        }
        //如果正好只有n个的话，就没有倒数n+1了，即此时的right为null,也代表删除的就是头节点
        if (right==null){
         return head.next;
        }
        //找到倒数n+1个节点(left指向的节点)
        while (right.next!=null){
            right=right.next;
            left=left.next;
        }
        left.next=left.next.next;
        return head;
    }

    //官网方法，巧妙使用哑节点，寻找倒数第n+1个节点(不需要像上面一样让left和right只相差n-1了)
    public static ListNode removeNthFromEnd(ListNode head, int n){
        ListNode node = new ListNode(-1);
        node.next=head;
        ListNode left=node;
        ListNode right=node;
        //让left和right相差n+1，找倒数第n+1个节点
        for (int i = 0; i < n+1; i++) { //当不存在第n+1个节点时，right为null,倒数第n+1个节点正好是哑节点
            right=right.next;
        }
        while (right!=null){
            right=right.next;
            left=left.next;
        }
        left.next=left.next.next;
        return node.next;
    }
}
