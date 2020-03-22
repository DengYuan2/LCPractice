package linkedList;

import java.util.Arrays;

//判断一个链表是否为回文链表【回文就是反转以后和以前一样的就是回文结构，例如 1->2->3->2->1】
//要求：用 O(n) 时间复杂度和 O(1) 空间复杂度
// https://leetcode-cn.com/problems/palindrome-linked-list/description/
public class E_234 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
//        n5.next = n6;
        boolean palindrome = isPalindrome(n1);
        System.out.println(palindrome);
    }

    //我的思路1：将链表反转，再一个个对比
    // 但该思路1有问题：1、新的链表将节点抢去了，原链表的结构已经被破坏了，没法比较 2、空间复杂度不是O(1)
    //我的思路2：只反转后半部分的节点，然后将后半部分已反转的节点和前半部分比较
    public static boolean isPalindrome(ListNode head){

        if (head==null||head.next==null){ //注：空的也是回文链表
            return true;
        }
        //获得节点数，确定中点的位置
        int count=0;
        ListNode cur=head;
        while (cur!=null){
            count++;
            cur=cur.next;
        }

        cur=head;
        for (int i = 0; i < count/2; i++) {
            cur=cur.next; //如果count为偶数，则直接到达第二部分的开头,此时第一部分的最后一个节点也是该开头，而且比较时前半部分也多这一个顶点
        }
        //如果count为奇数,cur还有再往后移一位才行
        if (!(count%2==0)){
            cur=cur.next;
        }
        //反转后半部分
        ListNode node = new ListNode(-1);
        ListNode temp =cur;
        while (cur!=null){
            temp=cur.next;
            cur.next=node.next;
            node.next=cur;
            cur=temp;
        }

        //比较前后两部分，只要比较前count/2个节点即可 【注：前后部分长度并不一定一致，可能相差1-2各节点】
        cur=head;
        temp=node.next;
        int c =1 ;
        while (c<=count/2){
            if (cur.val!=temp.val){
                return false;
            }
            cur=cur.next;
            temp=temp.next;
            c++;
        }
        return true;
    }
}
