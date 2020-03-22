package linkedList;

//链表求和1:逆序排的 https://leetcode-cn.com/problems/add-two-numbers/
//链表求和2:正序排的 M_445
public class M_2 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(6);
        ListNode n5 = new ListNode(9);
        ListNode n6 = new ListNode(7);
        ListNode n7 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        ListNode listNode = addTwoNumbers(n1, n4);
        System.out.println(listNode);
        System.out.println(addTwoNumbers(n3, n4));
        ListNode numbers2 = addTwoNumbers2(n1, n4);
        System.out.println(numbers2);
    }

    //其实是看了思路再自己写的,提交后错了一次，后来改对了
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int tail = 0;
        int val = 0;
        ListNode node = new ListNode(-1);
        ListNode temp = node;
        while (l1 != null && l2 != null) {
            val = l1.val + l2.val + tail;
            tail = val >= 10 ? 1 : 0;
            val %= 10;
            temp.next = new ListNode(val);
            l1 = l1.next;
            l2 = l2.next;
            temp = temp.next;
        }
        ListNode extra = l1 == null ? l2 : l1;
        while (extra != null) {
            val = extra.val + tail;
            tail = val >= 10 ? 1 : 0;
            val %= 10;
            temp.next = new ListNode(val);
            extra = extra.next;
            temp = temp.next;
        }
        //最后还有可能要进一位
        //todo 求和运算最后可能出现额外的进位，这一点很容易被遗忘
        if (tail == 1) {
            temp.next = new ListNode(tail);
        }
        return node.next;
    }

    //相同的思路，官方提供的更简洁的代码
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        ListNode node = new ListNode(-1);
        ListNode temp=node;
        int val=0;
        int v1=0;
        int v2=0;
        int tail=0;
        while (l1!=null||l2!=null){ //只要有一个节点不为空就能进行，对于已经为空的节点，值设为0
            v1=l1==null?0:l1.val;
            v2=l2==null?0:l2.val;
            val=v1+v2+tail;
            tail=val/10;
            temp.next=new ListNode(val%10);
            temp=temp.next;
            if (l1!=null){
                l1=l1.next;
            }
            if (l2!=null){
                l2=l2.next;
            }
        }
        if (tail==1){
            temp.next=new ListNode(tail);
        }
        return node.next;
    }

}
