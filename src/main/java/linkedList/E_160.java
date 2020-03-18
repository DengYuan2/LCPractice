package linkedList;

import javax.print.attribute.standard.NumberUp;
import java.util.List;

//找出两链表交点
//https://leetcode-cn.com/problems/intersection-of-two-linked-lists/description/
public class E_160 {
    public static void main(String[] args) {
        //同E_206的ListNode类
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node6.next = node4; //在4处相交
        ListNode intersectionNode = getIntersectionNode(node1, node6);
        System.out.println(intersectionNode);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //设a为第一个链表相交前的长度，b为第二个链表相交前的长度，c为两链表相交部分的长度
        //则：a+c+b=b+c+a
        //可参考图 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
        //该方法也适用于没有交点的情况，两人同为null退出
        if (headA==null||headB==null){
            return null;
        }
        ListNode A=headA;
        ListNode B=headB;
        while (A!=B){
//            if (A==null){
//                A=headB;
//            }else {
//                A=A.next;
//            }
//
//            if (B==null){
//                B=headA;
//            }else {
//                B=B.next;
//            }
            A=(A==null)?headB:A.next;
            B=(B==null)?headA:B.next;
        }

        return A;
    }
}
