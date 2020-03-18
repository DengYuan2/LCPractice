package Level_1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class E_206 {
    public static ListNode reverseList(ListNode head) {

        ListNode l1 = head;
        int length = 0;
        List<Integer> list = new ArrayList<Integer>();
        while (l1 != null) {
            list.add(l1.val);
            length++;
            l1 = l1.next;
        }
        l1=head;
        while (l1!=null){
            l1.val=list.get(length-1);
            length--;
            l1= l1.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(6);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        reverseList(n1);
        System.out.println(n1.toString());
    }

    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(6);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        System.out.println(n1.toString());
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
